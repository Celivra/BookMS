package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//有关提交的接口
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/doLogin")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request,
            RedirectAttributes reAttributes) {

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
        reAttributes.addFlashAttribute("PasswordError", true);
        //重定向到login页面
        return "redirect:/login";
    }

    @RequestMapping("/doRegister")
    public String doRegister(@ModelAttribute User user, RedirectAttributes reAttributes) {
        if(userService.addUser(user) == 1){
            reAttributes.addFlashAttribute("RegSuccess", "注册成功");
        }else if(userService.addUser(user) == 2){
            reAttributes.addFlashAttribute("RegUserIsExist", "用户已存在");
        }
        return "redirect:/register";
    }

    @PostMapping("/updateUser")
    public String updateUser(
            @RequestParam String userId,
            @RequestParam String userPhone,
            @RequestParam String userEmail,
            @RequestParam(required = false, defaultValue = "false") boolean userPower ){
        User user = userService.findByUserId(userId);
        user.setPhone(userPhone);
        user.setEmail(userEmail);
        if(userPower == true){
            user.setPower(10);
        }else{
            user.setPower(0);
        }
        userService.updateUser(user);
        return "redirect:/";
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

    @PostMapping("/deleteUser")
    public String deleteUser( @RequestParam String userId ){
        userService.deleteUser(userId);
        return "redirect:/";
    }
    @PostMapping("/changeAdminPassword")
    public String changeAdminPassword(@RequestParam String newPasswd, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("admin");
        if(userService.updatePassword(user.getUsername(), newPasswd)) {
            request.getSession().setAttribute("admin", userService.findByUsername(user.getUsername()));
        }
        return "redirect:/";
    }
}
