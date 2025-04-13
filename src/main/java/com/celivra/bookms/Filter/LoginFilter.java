package com.celivra.bookms.Filter;


import com.celivra.bookms.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        String path=req.getRequestURI();

        if(path.contains("/doLogin") || path.contains("/login") || path.contains("/css/") || path.contains("/js/") || path.contains("/register")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        User admin = (User) req.getSession().getAttribute("admin");
        if(user != null || admin != null){
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            resp.sendRedirect("/login");
        }
    }
}
