package com.tolfin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tolfin.web.pojo.Administrator;
import com.tolfin.web.mapper.AdministratorMapper;
import com.tolfin.web.pojo.User;
import com.tolfin.web.service.AdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-01
 */
@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    @Autowired
    AdministratorMapper administratorMapper;

    @Override
    public Administrator findByIdAndPassword(String id, String password) {
        QueryWrapper<Administrator> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("password",password);
        Administrator administrator = administratorMapper.selectOne(wrapper);
        return administrator;
    }
}
