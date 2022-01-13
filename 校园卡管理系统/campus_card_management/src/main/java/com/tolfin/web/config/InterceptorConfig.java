package com.tolfin.web.config;

import com.tolfin.web.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login","/css/**","/js/**","/images/**","/fonts/**",
                        "/lib/**","/favicon.ico","/error","/errorPage","/**/register","/user/checkCode","/")
        ;
    }
}
