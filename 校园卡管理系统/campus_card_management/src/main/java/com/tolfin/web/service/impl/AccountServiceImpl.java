package com.tolfin.web.service.impl;

import com.tolfin.web.pojo.Account;
import com.tolfin.web.mapper.AccountMapper;
import com.tolfin.web.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-03
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    Account account;

    @Override
    public Account findById(String id) {
        return accountMapper.selectById(id);
    }

    @Override
    public void insert(String id) {
        account.setId(id);
        account.setAccountBalance(0);
        account.setConsumptionToday(0);
        account.setLastRechargeAmount(0);
        accountMapper.insert(account);
    }
}
