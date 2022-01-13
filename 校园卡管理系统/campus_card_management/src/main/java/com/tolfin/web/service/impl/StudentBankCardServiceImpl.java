package com.tolfin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tolfin.web.pojo.StudentBankCard;
import com.tolfin.web.mapper.StudentBankCardMapper;
import com.tolfin.web.service.StudentBankCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StudentBankCardServiceImpl extends ServiceImpl<StudentBankCardMapper, StudentBankCard> implements StudentBankCardService {
    @Autowired
    StudentBankCardMapper studentBankCardMapper;
    @Override
    public List<StudentBankCard> findById(String sid) {
        QueryWrapper<StudentBankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("sid",sid);
        return studentBankCardMapper.selectList(wrapper);
    }

    @Override
    public int findCountByCid(String cid) {
        QueryWrapper<StudentBankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",cid);
        return studentBankCardMapper.selectCount(wrapper);
    }
}
