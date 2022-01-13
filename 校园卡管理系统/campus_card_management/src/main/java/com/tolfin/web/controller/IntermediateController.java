package com.tolfin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/intermedia")
@Controller
public class IntermediateController {

    @RequestMapping(value = "/login")
    public String userLogin(String identity){
        //身份判断
//        String identity = req.header("identity");
        if("adm".equals(identity)){
            return "forward:/administrator/login";
        }
        //默认身份为普通用户
        return "forward:/user/login";
    }
}
