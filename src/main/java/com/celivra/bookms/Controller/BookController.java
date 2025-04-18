package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.BookService;
import com.celivra.bookms.Service.BorrowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//有关书籍操作的接口
@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    BorrowService borrowService;

    @RequestMapping("/addBook")
    public String addBook(@RequestParam String bookName,
                          @RequestParam String author,
                          @RequestParam String bookType,
                          @RequestParam String publisher,
                          @RequestParam int bookNumber,
                          @RequestParam String description ) {
        Book book = new Book(bookName, author, bookType, publisher, bookNumber, description);
        bookService.addBook(book);
        return "redirect:/";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam String bookId){
        bookService.deleteBook(bookId);
        return "redirect:/";
    }

    @RequestMapping("/getBooks")
    public String getBooks(@RequestParam(value = "target",required = false) String target, HttpServletRequest request, Model model) {
        //根据target对books的各个字段进行查找
        List<Book> books = null;
        if(target != null) books = bookService.findAllBookByTarget(target);
        else books = bookService.getAllBooks();
        // 获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        // 获取当前用户所借的书
        List<Book> userbooks = borrowService.getUserBorrowedBooks(user.getId().toString());

        model.addAttribute("userbooks", userbooks);
        model.addAttribute("books", books);
        return "dashboard";
    }
}
