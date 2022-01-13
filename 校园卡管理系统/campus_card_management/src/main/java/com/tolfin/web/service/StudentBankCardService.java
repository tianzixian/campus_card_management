package com.tolfin.web.service;

import com.tolfin.web.pojo.StudentBankCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-04
 */
public interface StudentBankCardService extends IService<StudentBankCard> {

    /**
     * 查询学生的所有银行卡
     * @param sid
     * @return
     */
    List<StudentBankCard> findById(String sid);

    /**
     * 查询绑定此银行卡的学生人数
     * @param cid
     * @return
     */
    int findCountByCid(String cid);
}
