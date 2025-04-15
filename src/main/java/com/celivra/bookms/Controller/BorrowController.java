package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BorrowController {
    @Autowired
    BookMapper bookMapper;

    @RequestMapping("/borrow")
    public String borrow(@RequestParam String bookid, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Book book = bookMapper.findBookById(bookid);

        Borrow borrow = new Borrow(user.getId(), book.getId(), DateUtil.getCurrentDate(), null);
        return "redirect:/";
    }
}
