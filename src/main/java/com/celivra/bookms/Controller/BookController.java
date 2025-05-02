package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.*;
import com.celivra.bookms.Service.BookService;
import com.celivra.bookms.Service.BorrowService;
import com.celivra.bookms.Service.TicketService;
import com.celivra.bookms.Service.UserService;
import com.fasterxml.jackson.core.TreeCodec;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//有关书籍操作的接口
@Controller
public class BookController {

    /*===========实例化Service对象===============*/
    @Autowired
    BookService bookService;
    @Autowired
    BorrowService borrowService;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;
    /*===============实例化结束=================*/

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book, RedirectAttributes reAModel) {
        int flag = bookService.addBook(book);
        /*========================根据插入图书的返回值添加属性=================================*/
        if(flag == 2){
            reAModel.addFlashAttribute("AddSameBook","已经有一本同样的书籍存在!");
        }else if(flag == 0){
            reAModel.addFlashAttribute("AddBookError","因为系统原因添加失败!");
        }else{
            reAModel.addFlashAttribute("AddBookSuccess","添加图书成功");
        }
        /*===============================添加属性结束===================================*/

        reAModel.addFlashAttribute("activeSection", "books");
        return "redirect:/";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam String bookId, RedirectAttributes reAModel) {
        bookService.deleteBook(bookId);
        reAModel.addFlashAttribute("activeSection", "books");
        return "redirect:/";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book, RedirectAttributes reAModel) {
        /*=========================根据bookId获取书籍信息==============================*/
        Book originbook = bookService.getBookById(book.getId().toString());
        /*-----------------------------更新操作----------------------------------*/
        bookService.updateBook(originbook, book);
        /*===========================更新图书结束================================*/

        reAModel.addFlashAttribute("activeSection", "books");
        return "redirect:/";
    }

    @GetMapping("/getBooks")
    public String getBooks(@RequestParam(value = "target",required = false) String target, HttpServletRequest request, Model model) {
        /*===============================查找图书=======================================*/
        List<Book> books = null;
        if(target != null) books = bookService.getAllBookByTarget(target);
        else books = bookService.getAllBooks();
        model.addAttribute("books", books);
        /*=============================查找图书结束====================================*/

        User user = (User) request.getSession().getAttribute("user");

        /*=========================根据当前用户添加指定的属性====================================*/
        if(user != null){
            List<Book> userbooks = borrowService.getUserBorrowedBooks(user.getId().toString());
            List<BorrowInfo> borrowInfos = borrowService.getAllUserBorrows(user.getId().toString());
            List<Ticket> ticketList = ticketService.getAllTicket(user.getId());
            model.addAttribute("tickets", ticketList);
            model.addAttribute("borrowInfo", borrowInfos);
            model.addAttribute("userbooks", userbooks);
            return "dashboard";
        }
        /*---------------------------添加管理员所需要的属性----------------------------------*/
        List<User> userList = userService.getAllUsers();
        List<BorrowInfoAdmin> borrowInfoAdmins = borrowService.getAllBorrows();
        List<Ticket> ticketList = ticketService.getNoReplyTicket();
        model.addAttribute("users", userList);
        model.addAttribute("borrowInfo", borrowInfoAdmins);
        model.addAttribute("tickets", ticketList);
        model.addAttribute("activeSection", "books");
        return "admin";
        /*================================添加属性结束====================================*/
    }
}
