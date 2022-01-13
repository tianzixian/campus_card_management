package com.tolfin.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class GetCookie {
    public static Cookie getCookie(HttpServletRequest req , String cookieName){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie:
                cookies) {
            if (cookieName.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
