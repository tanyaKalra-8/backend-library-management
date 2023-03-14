package com.backend.librarymanagementsystem.Controller;

import com.backend.librarymanagementsystem.DTO.BookRequestDto;
import com.backend.librarymanagementsystem.DTO.BookResponseDto;
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
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto){

        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/get_books")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }
}
