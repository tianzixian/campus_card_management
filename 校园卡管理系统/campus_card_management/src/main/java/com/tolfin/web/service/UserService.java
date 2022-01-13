package com.tolfin.web.service;

import com.github.pagehelper.PageInfo;
import com.tolfin.web.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-01
 */
public interface UserService extends IService<User> {

    /**
     * 根据id和密码查找用户
     * @param id
     * @param password
     * @return
     */
    User findByIdAndPassword(String id, String password);

    /**
     * 更新用户信息
     * @param u
     */
    void updateUser(User u);

    /**
     * 添加一名用户
     * @param u
     */
    void insert(User u);

    /**
     * 查询全部学生信息
     * @return
     */
    List<User> findAll();

    /**
     * 通过id查询用户
     * @return
     * @param id
     */
    User findById(String id);

    /**
     * 模糊查询
     * @param column,info
     * @return  用户列表
     */
    List<User> blurQuery(String column , String info);

    /**
     * 分页模糊查询
     * @param column,info
     * @return  用户列表
     */
    List<User> blurQueryByPage(int pageNum, int pageSize , String column , String info);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUserById(String id);

    /**
     * 分页查询所有用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<User> findByPage(int pageNum, int pageSize);

    /**
     *  用户更新
     * @param id
     * @param name
     * @param major
     * @param sex
     * @param email
     * @param birthday
     * @param telephone
     */
    void modifyUserInfo(String id, String name, String major, String sex, String email, Date birthday, String telephone);

    /**
     * 更改密码
     * @param id
     * @param password
     */
    void updatePassword(String id, String password);
}
