package com.tolfin.web.controller;


import com.tolfin.web.pojo.Account;
import com.tolfin.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-03
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    Account account;
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable("id") String id , Model model){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("accountInfo");
        account = accountService.findById(id);
        mv.addObject("account",account);
//        model.addAttribute(account);
        return mv;
    }
}

