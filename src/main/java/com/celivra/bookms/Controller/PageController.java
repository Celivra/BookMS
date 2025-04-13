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

    @Autowired
    UserMapper userMapper;
    @Autowired
    private FlashMapManager flashMapManager;

    @RequestMapping("/")
    public String dashboard(HttpServletRequest req) {
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request ) {

        User user = userMapper.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            if(user.getPower() == 10){
                request.getSession().setAttribute("admin", user);
                return "redirect:/admin";
            }
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("/register")
    public String Register(){
        return "register";
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest req){
        if(req.getSession().getAttribute("admin") != null){
            return "admin";
        }
        return "redirect:/";
    }
}
