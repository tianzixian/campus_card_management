package com.tolfin.web.controller;


import com.tolfin.web.pojo.BankCard;
import com.tolfin.web.utils.ResultInfo;
import com.tolfin.web.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/bankcard")
public class BankCardController {
    @Autowired
    BankCard bankCard;
    @Autowired
    BankCardService bankCardService;
    @Autowired
    ResultInfo info;

    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("onlineRecharge");
        if(req.getAttribute("noInfo")!=null || "T".equals((String)req.getAttribute("noInfo"))){
            mv.addObject("notfind","还未绑定银行卡");
        }else{
            List<String> cids =(List<String>)req.getAttribute("cardIds");
            List<BankCard> bankCardList = bankCardService.findByBids(cids);
            mv.addObject("bankCardList",bankCardList);
        }
        return mv;
    }

    @RequestMapping(value = "/recharge")
    public Object recharge(HttpServletRequest req){
//        ModelAndView mv = new ModelAndView();
        try {
            String cid = req.getParameter("cardId");
            String sid = req.getParameter("sid");
            //amount of money 需要充入的金额
            int aom = Integer.parseInt(req.getParameter("aom"));
            String password = req.getParameter("password");
            //根据银行 卡id + 密码 校验银行卡
            BankCard bankCard = bankCardService.findByCidAndPassword(cid,password);
            if(null == bankCard){  //用户输入的密码错误
                info.setFlag(false);
                info.setErrorMsg("输入的密码错误！");
            }else{
                //用户银行卡余额不足
                if(bankCard.getBalance() < aom){
                    info.setFlag(false);
                    info.setErrorMsg("银行卡余额不足!");
                }else{
                    info.setFlag(true);
                    info.setData(sid);
                    //余额足够可以充值
                    bankCardService.transfer(bankCard,aom,sid);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {}
        return info;
    }

}

