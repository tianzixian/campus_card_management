package com.tolfin.web.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tolfin.web.utils.GetCookie;
import com.tolfin.web.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String requestURI = request.getRequestURI();
        log.info("当前uri为：{}", requestURI);

        String token = null;
        try{
             token = GetCookie.getCookie(request,"token").getValue();
        }catch (Exception e){
            e.printStackTrace();
        }

        //获取请求头中的令牌
//        String token = request.getHeader("token");
        log.info("当前token为：{}", token);

        try {
            DecodedJWT verify = JWTUtils.verify(token);
            String identity = verify.getClaim("identity").asString();
            if("ord".equals(identity)){  //用户身份判断
                // 以上路径不允许访问 /adminOpt  /user/findByCondition
                if(requestURI.indexOf("/adminOpt")!=-1 || requestURI.indexOf("/user/findByCondition")!=-1){
                    map.put("msg", "没有访问权限！");
                }else return true;
            }else
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "签名不一致");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "令牌过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不匹配");
        } catch (InvalidClaimException e) {
            e.printStackTrace();
            map.put("msg", "失效的payload");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效");
        }

        map.put("state", false);

        //响应到前台: 将map转为json
        //String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        request.setAttribute("error_msg",map.get("msg"));
        //1.调用HttpServletRequest的getRequestDispatcher()方法，调用时需要传入转发的地址;
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/errorPage");
        //2.调用HttpServletRequest的forward(request,response)方法进行请求的转发;
        requestDispatcher.forward(request,response);

        //response.getWriter().println(json);
        return false;
    }
}
