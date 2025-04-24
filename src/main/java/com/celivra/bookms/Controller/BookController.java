package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.BookService;
import com.celivra.bookms.Service.BorrowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book, Model model) {
//        Book book = new Book(bookName, author, bookType, publisher, bookNumber, description, isbn, publishedDate);
        bookService.addBook(book);
        return "redirect:/";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam String bookId){
        bookService.deleteBook(bookId);
        return "redirect:/";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book, Model model) {
        Book originbook = bookService.findBookById(book.getId().toString());
        originbook.setBookName(book.getBookName());
        originbook.setAuthor(book.getAuthor());
        originbook.setBookType(book.getBookType());
        originbook.setPublisher(book.getPublisher());
        originbook.setBookNumber(book.getBookNumber());
        originbook.setDescription(book.getDescription());
        originbook.setISBN(book.getISBN());
        originbook.setPublishedDate(book.getPublishedDate());
        bookService.updateBook(originbook);
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
