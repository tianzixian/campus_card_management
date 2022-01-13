package com.tolfin.web.service;

import com.tolfin.web.pojo.BankCard;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tolfin.web.pojo.StudentBankCard;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-04
 */
public interface BankCardService extends IService<BankCard> {
    /**
     * 根据银行卡bid查询用户的银行卡
     * @param cids
     * @return
     */
    List<BankCard> findByBids(List<String> cids);

    /**
     * 根据银行 卡id + 密码 校验银行卡
     * @param cid
     * @param password
     * @return
     */
    BankCard findByCidAndPassword(String cid, String password);

    /**
     * 银行卡向校园卡转账操作
     * @param bankCard
     * @param aom
     * @param sid
     */
    void transfer(BankCard bankCard, Integer aom , String sid);
}
