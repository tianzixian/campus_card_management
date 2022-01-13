package com.tolfin.web.controller;


import com.tolfin.web.pojo.BankCard;
import com.tolfin.web.pojo.StudentBankCard;
import com.tolfin.web.service.StudentBankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-04
 */
@RestController
@RequestMapping("/sbc")
public class StudentBankCardController {
    @Autowired
    StudentBankCard studentBankCard;
    @Autowired
    StudentBankCardService studentBankCardService;

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable("id") String sid , HttpServletRequest req){
        ModelAndView mv = new ModelAndView("forward:/bankcard/info");
        List<StudentBankCard> sbcList = studentBankCardService.findById(sid);
        if(sbcList.size()==0){
            req.setAttribute("noInfo","T");
        }
        List<String> ids = new ArrayList<>();
        for ( StudentBankCard sbc : sbcList) {
            ids.add(sbc.getCid());
        }
        req.setAttribute("cardIds",ids);
        mv.addObject("sid",sid);
        //mv.addObject("sid",sid);
//        return "forward:/bankcard/info";
        return mv;
    }
}

