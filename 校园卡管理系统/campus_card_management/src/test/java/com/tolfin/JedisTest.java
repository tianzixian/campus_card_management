package com.tolfin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tolfin.web.config.RedisConfiguration;
import com.tolfin.web.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.*;

@SpringBootTest
public class JedisTest {
    @Autowired
    JedisPool jedisPool;

    @Test
    public void test01(){
        Jedis resource = jedisPool.getResource();
        resource.set("test","sprigboot");
        String test = resource.get("test");
        System.out.println(test);
    }

    @Test
    public void test02(){
        List<User> userList = new ArrayList<>();
        Date date = new Date();
        User user1 = new User("1","1","1","1",1,"1","1",date,"1");
        User user2 = new User("2","1","1","1",1,"1","1",date,"1");
        User user3 = new User("3","1","1","1",1,"1","1",date,"1");
        User user4 = new User("4","1","1","1",1,"1","1",date,"1");
        User user5 = new User("5","1","1","1",1,"1","1",date,"1");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(userList);
            Jedis resource = jedisPool.getResource();
            resource.set("users",json);

            String users = resource.get("users");
            System.out.println(users);

            List<User> ul = objectMapper.readValue(users,List.class);
            System.out.println(ul.size());
            System.out.println(Arrays.toString(ul.toArray()));

        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
