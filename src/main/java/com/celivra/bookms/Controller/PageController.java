package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMapManager;

@Controller
public class PageController {

    @RequestMapping("/")
    public String dashboard(HttpServletRequest req) {
        if(req.getSession().getAttribute("admin") != null){
            return "admin";
        }else{
            return "dashboard";
        }
    }
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/register")
    public String Register(){
        return "register";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req){
        if(req.getSession().getAttribute("admin") != null){
            req.getSession().setAttribute("admin", null);
        }
        if(req.getSession().getAttribute("user") != null){
            req.getSession().setAttribute("user", null);
        }
        return "redirect:/";
    }

}
