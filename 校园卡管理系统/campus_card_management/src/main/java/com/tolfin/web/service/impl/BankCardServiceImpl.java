package com.tolfin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tolfin.web.mapper.AccountMapper;
import com.tolfin.web.pojo.Account;
import com.tolfin.web.pojo.BankCard;
import com.tolfin.web.mapper.BankCardMapper;
import com.tolfin.web.pojo.StudentBankCard;
import com.tolfin.web.service.BankCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-04
 */
@Service
public class BankCardServiceImpl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardService {
    @Autowired
    BankCardMapper bankCardMapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<BankCard> findByBids(List<String> cids) {
        List<BankCard> bankCards = bankCardMapper.selectBatchIds(cids);
        System.out.println(Arrays.toString(bankCards.toArray()));
        return bankCards;
    }

    @Override
    public BankCard findByCidAndPassword(String cid, String password) {
        QueryWrapper<BankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",cid)
                .eq("cpassword",password);
        return bankCardMapper.selectOne(wrapper);
    }

    @Override
    public void transfer(BankCard bankCard , Integer aom , String sid) {
        //从银行卡往校园卡转帐，同时帐户信息中末次充值情况将自动更新
        //转账操作 银行卡余额减少
        Integer newBalance = bankCard.getBalance() - aom;
        bankCard.setBalance(newBalance);
        System.out.println(bankCard.toString());
        bankCardMapper.updateById(bankCard);

        //末次充值情况将更新
        Account account = accountMapper.selectById(sid);
        //账户余额增加
        account.setAccountBalance(account.getAccountBalance()+aom);
        //末次充值金额更新
        account.setLastRechargeAmount(aom);
        //末次充值时间更新
        account.setLastRechargeTime(new Date());
        accountMapper.updateById(account);
    }
}
