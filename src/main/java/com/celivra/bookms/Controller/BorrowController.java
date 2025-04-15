package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//有关借阅的接口
@Controller
public class BorrowController {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowMapper borrowMapper;

    @RequestMapping("/borrow")
    public String borrow(@RequestParam String bookid, HttpServletRequest request) {
        //从session里获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        //根据bookid获取选中的书籍信息
        Book book = bookMapper.findBookById(bookid);

        //建立借阅记录
        Borrow borrow = new Borrow(user.getId(), book.getId(), DateUtil.getCurrentDate(), null);
        //将记录插入到数据库里
        borrowMapper.insertBorrow(borrow);
        return "redirect:/";
    }
}
