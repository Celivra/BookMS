package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.BorrowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//有关借阅的接口
@Controller
public class BorrowController {

    /*===========实例化Service对象===============*/
    @Autowired
    BorrowService borrowService;
    /*===============实例化结束=================*/

    //归还图书
    @PostMapping("/returnBook")
    public String returnBook(@RequestParam String bookid, HttpServletRequest request, RedirectAttributes reAModel) {
        User user = (User) request.getSession().getAttribute("user");

        /*======================进行归还操作，根据返回值添加flash属性===========================*/
        if(borrowService.returnBook(bookid, user.getId().toString())) {
            reAModel.addFlashAttribute("Returned", "归还成功");
        }
        /*===============================归还操作结束===================================*/

        reAModel.addFlashAttribute("activeSection", "mybook");
        return "redirect:/";
    }
    //借阅图书
    @PostMapping("/borrowBook")
    public String borrow(@RequestParam String bookid, HttpServletRequest request, RedirectAttributes reAttributes) {
        User user = (User) request.getSession().getAttribute("user");
        /*=========================进行借阅操作，如果成功则添加属性==============================*/
        if (!borrowService.borrowBook(bookid, user)) {
            reAttributes.addFlashAttribute("Borrowed", true);
        }
        /*===============================借阅操作结束=================================*/
        return "redirect:/";
    }
}
