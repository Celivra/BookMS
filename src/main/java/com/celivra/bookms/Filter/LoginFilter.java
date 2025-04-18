package com.celivra.bookms.Filter;


import com.celivra.bookms.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

//登入过滤器
@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取当前的req和resp
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;

        //从当前req中获取当前访问的接口路径
        String path=req.getRequestURI();

        //判断访问的接口是否为dologin、login...等
        if(path.contains("/doLogin") || path.contains("/login") || path.contains("/css/") || path.contains("/js/") || path.contains("/register") || path.contains("/doRegister")){
            //直接放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //获取当前存在的用户信息
        User user = (User) req.getSession().getAttribute("user");
        User admin = (User) req.getSession().getAttribute("admin");

        //若存在user或者admin，则放行
        //否则跳转到/login提示用户进行登入
        if(user != null || admin != null){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            resp.sendRedirect("/login");
        }
    }
}
