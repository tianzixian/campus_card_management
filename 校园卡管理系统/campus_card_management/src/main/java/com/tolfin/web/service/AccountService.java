package com.tolfin.web.service;

import com.tolfin.web.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-03
 */
public interface AccountService extends IService<Account> {

    /**
     * 通过id查询账户信息
     * @param id
     * @return
     */
    Account findById(String id);

    /**
     * 增加一张校园卡
     * @param id
     */
    void insert(String id);
}
