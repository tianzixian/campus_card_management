package com.tolfin.web.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tolfin.web.utils.ResultInfo;
import com.tolfin.web.pojo.User;
import com.tolfin.web.service.UserService;
import com.tolfin.web.utils.CaculateAge;
import com.tolfin.web.utils.GetCookie;
import com.tolfin.web.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-01
 */

@RequestMapping(value = "/user")
@RestController
public class UserController {
    @Autowired
    User user;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    public Object login(String id, String password,HttpServletRequest req){
        ResultInfo info = new ResultInfo();
        //校验验证码是否正确
        String checkCode = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //将验证码从session中移除，验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkCode)){
            //验证码不正确
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            return info;
        }

        user = userService.findByIdAndPassword(id,password);
        if(null == user){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
            return info;
        }
        info.setFlag(true);
        Map<String, String> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("name", user.getName());
        payload.put("identity","ord");
        String token = JWTUtils.getToken(payload);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("identity","ord");
        map.put("sid",id);
        info.setData(map);
        return info;
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView userInfo(@PathVariable("id") String id ,HttpServletRequest req){
        ModelAndView mv = new ModelAndView();
        user = userService.findById(id);
        mv.addObject("user",user);
        if ("adm".equals(getIdentity(req))){
        mv.setViewName("userInfo");
        }else{
            mv.setViewName("userModify");
        }
        return mv;
    }

    @RequestMapping(value = "/update")
    public ModelAndView updateUser(User u){
        ModelAndView mv = new ModelAndView();
        userService.updateUser(u);
        mv.setViewName("userInfo");
        return mv;
    }

    //用户修改个人信息
    @RequestMapping(value = "/modify")
    public ModelAndView modifyUser(@RequestParam(value = "id",required = false) String id,
                @RequestParam(value = "name",required = false) String name,
                @RequestParam(value = "major",required = false) String major,
                @RequestParam(value = "sex",required = false) String sex,
                @RequestParam(value = "email",required = false) String email,
                @RequestParam(value = "birthday",required = false) Date birthday,
                @RequestParam(value = "telephone",required = false) String telephone,
                HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView();
        userService.modifyUserInfo(id,name,major,sex,email,birthday,telephone);
        mv.setViewName("userModify");
        mv.addObject("user",userService.findById(id));
        return mv;
    }


    //用户注册
    @RequestMapping(value = "/register")
    public Object register(@RequestParam(value = "id",required = false) String id,
                           @RequestParam(value = "password",required = false) String password,
                           @RequestParam(value = "name",required = false) String name,
                           @RequestParam(value = "major",required = false) String major,
                           @RequestParam(value = "sex",required = false) String sex,
                           @RequestParam(value = "email",required = false) String email,
                           @RequestParam(value = "birthday",required = false) Date birthday,
                           @RequestParam(value = "telephone",required = false) String telephone,
                           HttpServletRequest req) throws Exception {

        ResultInfo info = new ResultInfo();
            //查看id是否重复
            User fu = userService.findById(id);
            if(null!=fu){
                info.setFlag(false);
                info.setErrorMsg("学工号已经存在！");
                return info;
            }

        info.setFlag(true);
        //根据出生日期计算年龄
        Integer age = CaculateAge.getAge(birthday);
        User u = new User(id,password,name,major,age,sex,email,birthday,telephone);
        userService.insert(u);
        info.setData(getIdentity(req));
        return info;
    }


    @RequestMapping(value = {"/findByCondition/{pageNum}/{condition}/{info}","/findByCondition/{pageNum}"})
    public ModelAndView findByCondition(@PathVariable(value = "pageNum",required = true) Integer pageNum ,
                                        @PathVariable(value = "condition",required = false) String condition,
                                        @PathVariable(value = "info",required = false) String info,
                                        HttpServletRequest req ){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("adminOpt");

        if (null==condition) condition = req.getParameter("condition");
        if (null==info) info = req.getParameter("info");
        if("null".equals(info)) info = "";

        List<User> userlist = null ; //存放查询的用户信息
        List<User> pagelist = null ; //存放页面显示信息

        //匹配condition进行模糊查询
            if("学工号".equals(condition)){
                userlist = userService.blurQuery("id",info);
            }else if("姓名".equals(condition)){
                userlist = userService.blurQuery("name",info);
            }else if("学院".equals(condition)){
                userlist = userService.blurQuery("major",info);
            }else if("电话".equals(condition)){
                userlist = userService.blurQuery("telephone",info);
            }else if("邮箱".equals(condition)){
                userlist = userService.blurQuery("email",info);
            }else{
                userlist = userService.findAll();
            }

        int pageSize = 5;
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(userlist);
        pageInfo.setPageSize(pageSize);
        int total = (int)(pageInfo.getTotal());
        int pages = total==0?0:total<pageSize?1:total%pageSize == 0 ? total/pageSize : total/pageSize + 1;
        pageInfo.setPages(pages);
        if(pageNum<1) {
            pageNum = 1;
        }
        if((pageNum-1)*pageSize > total){
            pageNum = pages; //去末页
        }
        pageInfo.setPageNum(pageNum);

        //分页查询
        //匹配condition进行模糊查询
            if("学工号".equals(condition)){
                pagelist = userService.blurQueryByPage(pageNum,pageSize,"id",info);
            }else if("姓名".equals(condition)){
                pagelist = userService.blurQueryByPage(pageNum,pageSize,"name",info);
            }else if("学院".equals(condition)){
                pagelist = userService.blurQueryByPage(pageNum,pageSize,"major",info);
            }else if("电话".equals(condition)){
                pagelist = userService.blurQueryByPage(pageNum,pageSize,"telephone",info);
            }else if("邮箱".equals(condition)){
                pagelist = userService.blurQueryByPage(pageNum,pageSize,"email",info);
            }else {
                pagelist = userService.findByPage(pageNum,pageSize); //分页查询结果
            }
        System.out.println(pageInfo);
//        List<User> users = userService.findByPage(pageNum,pageSize); //分页查询结果
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("users",pagelist);
        mv.addObject("condition",condition);
        mv.addObject("info",info);
        return mv;
    }


    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/adminOpt");
        userService.deleteUserById(id);
        return mv;
    }

    @RequestMapping(value = "/opt/{id}")
    public ModelAndView opt(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userOpt");
        mv.getModel().put("sid",id);
        return mv;
    }

    @RequestMapping(value = "/findOne")
    public Object findOne(HttpServletRequest req){
        String token = GetCookie.getCookie(req, "token").getValue();
        HashMap<String, String> map = new HashMap<>();
        //查找当前登录用户
        DecodedJWT verify = JWTUtils.verify(token);
        String username = verify.getClaim("name").asString();
        String id = verify.getClaim("id").asString();
        String identity = verify.getClaim("identity").asString();
        map.put("name",username);
        map.put("id",id);
        map.put("identity",identity);
        return map;
    }

    @RequestMapping(value = "/exit")
    public ModelAndView exit(HttpServletRequest req , HttpServletResponse resp){
        ModelAndView mv = new ModelAndView("redirect:/login");
        //删除cookie中的token
        resp.addCookie(killMyCookie("token"));
        return mv;
    }

    @RequestMapping(value = "/updatePassword/{id}")
    public ModelAndView toUpdatePasswordPage(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userUpdatePassword");
        mv.addObject("user",userService.findById(id));
        return mv;
    }

    @RequestMapping(value = "/updatePassword")
    public Object updatePassword(@RequestParam(value = "confirmPassword",required = true) String password,
                                 @RequestParam(value = "id",required = true) String id,
                                 HttpServletResponse response){
        userService.updatePassword(id,password);
        ResultInfo resultInfo = new ResultInfo();
//        resultInfo.setData();
        resultInfo.setFlag(true);
//        删除用户登录信息
        response.addCookie(killMyCookie("token"));
        return resultInfo;
    }

    public String getIdentity(HttpServletRequest req){
        //判断是否为管理员操作 若是则前台跳转到adminOpt
        Cookie cookie = GetCookie.getCookie(req, "token");
        if(null!=cookie){  //当前处于用户登录状态
            String token = cookie.getValue();
            //查找当前登录用户
            DecodedJWT verify = JWTUtils.verify(token);
            String identity = verify.getClaim("identity").asString();
            return identity;
        }
        return null;
    }

    public Cookie killMyCookie(String name){
        Cookie killMyCookie = new Cookie(name, null);
        killMyCookie.setMaxAge(0);
        killMyCookie.setPath("/");
        return killMyCookie;
    }


    /**
     * 生成验证码图片
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        //服务器通知浏览器不要缓存
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        req.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",resp.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

}

