package com.tolfin.web.controller;


import com.tolfin.web.utils.ResultInfo;
import com.tolfin.web.pojo.Administrator;
import com.tolfin.web.service.AdministratorService;
import com.tolfin.web.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-01
 */
@RequestMapping("/administrator")
@RestController
public class AdministratorController {

    @Autowired
    Administrator administrator;
    @Autowired
    AdministratorService administratorService;

    @RequestMapping("/login")
    public Object login(HttpServletRequest request, String id, String password){
        ResultInfo info = new ResultInfo();
        administrator = administratorService.findByIdAndPassword(id,password);
        if(null == administrator){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
            return info;
        }
        info.setFlag(true);
        Map<String, String> payload = new HashMap<>();
        payload.put("id", administrator.getId());
        payload.put("name", administrator.getName());
        payload.put("identity","adm");
        String token = JWTUtils.getToken(payload);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("identity","adm");
        info.setData(map);
        return info;
    }

}

