package com.tolfin.web.service;

import com.tolfin.web.pojo.Administrator;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-01
 */
public interface AdministratorService extends IService<Administrator> {
    /**
     * 根据id和密码查询管理员信息
     * @param id
     * @return
     */
    Administrator findByIdAndPassword(String id, String password);
}
