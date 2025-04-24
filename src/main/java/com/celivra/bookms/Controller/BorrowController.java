package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.BorrowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//有关借阅的接口
@Controller
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    //归还图书
    @PostMapping("/returnBook")
    public String returnBook(@RequestParam String bookid, HttpServletRequest request, Model model) {
        //获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        //调用归还图书的Service
        boolean success = borrowService.returnBook(bookid, user.getId().toString());
        //如果成功就添加一个“Returned”属性，供前端进行判断
        if(success) {
            model.addAttribute("Returned", true);
        }
        return "redirect:/";
    }
    //借阅图书
    @PostMapping("/borrowBook")
    public String borrow(@RequestParam String bookid, HttpServletRequest request, RedirectAttributes reAttributes) {
        User user = (User) request.getSession().getAttribute("user");
        //添加一条借阅记录
        boolean success = borrowService.borrowBook(bookid, user);
        if (!success) {
            //添加flash attribute，当第一次读取后接着删除
            reAttributes.addFlashAttribute("Borrowed", true);
        }
        return "redirect:/";
    }
}
