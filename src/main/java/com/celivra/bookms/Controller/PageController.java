package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Mapper.BookMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

//页面跳转
@Controller
public class PageController {

    @Autowired
    BookMapper bookMapper;

    @RequestMapping("/")
    public String dashboard(HttpServletRequest req, Model model) {
        //加载/页面时获取所有书籍的信息
        List<Book> bookList = bookMapper.getAllBooks();
        //将获取到的所有数据放到model里，供前端调用
        model.addAttribute("books", bookList);
        //判断当前session里的是admin还是user,分别返回各自的页面
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
        //首先判断session里是否存在admin或者user的attribute，如果存在就设为null
        if(req.getSession().getAttribute("admin") != null){
            req.getSession().setAttribute("admin", null);
        }
        if(req.getSession().getAttribute("user") != null){
            req.getSession().setAttribute("user", null);
        }
        return "redirect:/";
    }

}
