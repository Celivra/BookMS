package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
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
        System.out.println(username + " " + password);
        if("admin".equals(username) && "admin".equals(password)){
            request.getSession().setAttribute("user", new User(username, password));
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("/register")
    public String Register(){
        return "register";
    }
}
