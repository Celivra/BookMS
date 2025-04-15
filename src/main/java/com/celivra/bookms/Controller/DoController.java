package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/doLogin")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {

        User user = userMapper.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            if(user.getPower() == 10){
                request.getSession().setAttribute("admin", user);
            }else {
                request.getSession().setAttribute("user", user);
            }
            return "redirect:/";
        }
        model.addAttribute("PasswordError", true);
        return "login";
    }

    @RequestMapping("/doRegister")
    public String doRegister(){
        return "redirect:/login";
    }

    @RequestMapping("/doChangeInfo")
    public String doChangeInfo(
            @RequestParam String newPhone,
            @RequestParam String newEmail,
            HttpServletRequest request ){

        User user = (User) request.getSession().getAttribute("user");
        if(userMapper.updateInfo(user.getUsername(), newPhone, newEmail)){
            request.getSession().setAttribute("user", userMapper.findByUsername(user.getUsername()));
        }
        return "redirect:/";
    }

    @RequestMapping("/doChangePassword")
    public String doChangePassword(@RequestParam String newPasswd, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(userMapper.updatePassword(user.getUsername(), newPasswd)){
            request.getSession().setAttribute("user", userMapper.findByUsername(user.getUsername()));
        }
        return "redirect:/";
    }


}
