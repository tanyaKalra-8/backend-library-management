package com.backend.librarymanagementsystem.Controller;

import com.backend.librarymanagementsystem.Entity.Book;
import com.backend.librarymanagementsystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){

        try{
            bookService.addBook(book);
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage() + " Book not added");
        }
        return "Book added successfully";
    }

    @GetMapping("/get_books")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }
}
