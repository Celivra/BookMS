package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.UserMapper;
import com.celivra.bookms.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//有关提交的接口
@Controller
public class DoController {
    @Autowired
    UserService userService;

    @RequestMapping("/doLogin")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            Model model) {

        //根据用户名获取用户数据
        User user = userService.findByUsername(username);
        //判断有无这个用户或者密码是否匹配
        if(user != null && user.getPassword().equals(password)) {
            //判断是否为管理员账户, 创建不同的attribute
            if(user.getPower() == 10){
                request.getSession().setAttribute("admin", user);
            }else {
                request.getSession().setAttribute("user", user);
            }
            //重定向到/目录
            return "redirect:/";
        }
        //若没有进入if里，则密码错误
        //创建passworderror错误
        model.addAttribute("PasswordError", true);
        //重定向到login页面
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

        //从session里拿到当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        //判断是否修改成功
        if(userService.updateInfo(user.getUsername(), newPhone, newEmail)) {
            //将新的user数据加载到session里
            request.getSession().setAttribute("user", userService.findByUsername(user.getUsername()));
        }
        return "redirect:/";
    }

    @RequestMapping("/doChangePassword")
    public String doChangePassword(@RequestParam String newPasswd, HttpServletRequest request){
        //从session里获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        //判断修改是否成功
        if(userService.updatePassword(user.getUsername(), newPasswd)) {
            //将新的user数据加载到session里
            request.getSession().setAttribute("user", userService.findByUsername(user.getUsername()));
        }
        return "redirect:/";
    }


}
