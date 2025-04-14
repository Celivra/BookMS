package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMapManager;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    BookMapper bookMapper;

    @RequestMapping("/")
    public String dashboard(HttpServletRequest req, Model model) {

        List<Book> bookList = bookMapper.getAllBooks();
        model.addAttribute("books", bookList);
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
