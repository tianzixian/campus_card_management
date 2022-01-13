package com.tolfin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tolfin.web.config.RedisConfiguration;
import com.tolfin.web.mapper.AccountMapper;
import com.tolfin.web.mapper.BankCardMapper;
import com.tolfin.web.mapper.StudentBankCardMapper;
import com.tolfin.web.pojo.BankCard;
import com.tolfin.web.pojo.StudentBankCard;
import com.tolfin.web.pojo.User;
import com.tolfin.web.mapper.UserMapper;
import com.tolfin.web.service.BankCardService;
import com.tolfin.web.service.StudentBankCardService;
import com.tolfin.web.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tolfin.web.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-01
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BankCardMapper bankCardMapper;
    @Autowired
    StudentBankCardService studentBankCardService;
    @Autowired
    StudentBankCardMapper studentBankCardMapper;
    @Autowired
    JedisPool jedisPool;
    @Autowired
    User user;

    public List<User> blurLookup(String column , String info){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(column,info);
        return userMapper.selectList(wrapper);
    }

    @Override
    public User findByIdAndPassword(String id, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("password",password);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public void updateUser(User u) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",u.getId());
        userMapper.update(u,wrapper);
        updateRedis();
    }

    @Override
    public void insert(User u) {
        //注册用户
        userMapper.insert(u);
//        为用户添加一张校园卡
        accountService.insert(u.getId());
        updateRedis();
    }

    @Override
    public List<User> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        //利用redis读取数据 需要先开启redis服务器
        Jedis jedis = jedisPool.getResource();
        //查询redis数据库
        String users = jedis.get("users");
        List<User> userList = null;
        if(users == null || users.length() ==0 ){    //redis中没有数据，在mysql中查询后存入redis
            log.info("redis中没有数据，在mysql中查询后存入redis");
            userList = userMapper.selectList(null);
            try {
                String json = objectMapper.writeValueAsString(userList);//用户数组转json
                //存储
                jedis.set("users",json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{         //redis有数据，返回 List<>...
            log.info("redis有数据");
            try {
                userList = objectMapper.readValue(users,List.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        jedis.close();
        return userList;
//        return userMapper.selectList(null);
    }

    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> blurQuery(String column, String info) {
        return blurLookup(column,info);
    }

    @Override
    public List<User> blurQueryByPage(int pageNum, int pageSize , String column , String info) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(column,info);
        String lastsql = " limit "+ (pageNum-1)*pageSize + " , "+ pageSize;
        wrapper.last(lastsql);
        return userMapper.selectList(wrapper);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        //删除用户信息同时删除校园卡账户和银行卡信息
        //删除用户信息
        userMapper.deleteById(id);
        //删除校园卡账户信息
        accountMapper.deleteById(id);
        //删除学生学号与银行卡的映射信息
        List<StudentBankCard> cardEntries = studentBankCardService.findById(id);
        for (int i = 0; i < cardEntries.size(); i++) {
            //若一张银行卡绑定了多个学生 则不删除这张银行卡的信息
            StudentBankCard sbc = cardEntries.get(i);
            int count = studentBankCardService.findCountByCid(sbc.getCid());
            //删除映射
            QueryWrapper<StudentBankCard> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("sid",sbc.getSid()).eq("cid",sbc.getCid());
            studentBankCardMapper.delete(wrapper1);
            if(count <= 1){  //有多人绑定 不删除bank_card中字段
                //可以删除银行卡
                // 删除银行卡信息
                QueryWrapper<BankCard> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("cid",sbc.getCid());
                bankCardMapper.delete(wrapper2);
            }
        }
        updateRedis();
    }

    @Override
    public List<User> findByPage(int pageNum, int pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String lastsql = "limit "+ (pageNum-1)*pageSize + " , "+ pageSize;
        wrapper.last(lastsql);
        return userMapper.selectList(wrapper);
    }

    @Override
    public void modifyUserInfo(String id, String name, String major, String sex, String email, Date birthday, String telephone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        User u = findById(id);
        u.setName(name);
        u.setMajor(major);
        u.setSex(sex);
        u.setEmail(email);
        u.setBirthday(birthday);
        u.setTelephone(telephone);
        userMapper.update(u,wrapper);
        updateRedis();
    }

    @Override
    public void updatePassword(String id, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        userMapper.update(findById(id).setPassword(password),wrapper);
    }

    //更新redis数据
    public void updateRedis() {
        Jedis resource = jedisPool.getResource();
        String json = null;//用户数组转json
        try {
            json = new ObjectMapper().writeValueAsString(findUsers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        resource.set("users",json);
        resource.close();
    }
    //不通过redis在mysql中查询所有用户
    public List<User> findUsers(){
        return userMapper.selectList(null);
    }

}
