package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Service.BorrowService;
import com.celivra.bookms.Util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//有关借阅的接口
@Controller
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @RequestMapping("/borrow")
    public String borrow(@RequestParam String bookid, HttpServletRequest request, RedirectAttributes reAttributes) {
        User user = (User) request.getSession().getAttribute("user");
        boolean success = borrowService.borrowBook(bookid, user);
        if (!success) {
            reAttributes.addFlashAttribute("Borrowed", true);
        }
        return "redirect:/";
    }
}
