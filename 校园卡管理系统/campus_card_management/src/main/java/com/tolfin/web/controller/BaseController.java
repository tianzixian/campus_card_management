package com.tolfin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/userOpt")
    public String userOpt(){
        return "userOpt";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(){
        return "userInfo";
    }

    @RequestMapping(value = "/onlineRecharge")
    public String onlineRecharge(){
        return "onlineRecharge";
    }

    @RequestMapping(value = "/errorPage")
    public String errorPage(){
        return "errorPage";
    }

    @RequestMapping(value = "/adminOpt")
    public String adminOpt(){
        return "adminOpt";
    }

    @RequestMapping(value = "/userModify")
    public String userModify(){
        return "userModify";
    }

    @RequestMapping(value = "/userUpdatePassword")
    public String userUpdatePassword(){
        return "userUpdatePassword";
    }

}
