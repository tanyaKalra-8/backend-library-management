package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.DTO.BookRequestDto;
import com.backend.librarymanagementsystem.DTO.BookResponseDto;
import com.backend.librarymanagementsystem.Entity.Author;
import com.backend.librarymanagementsystem.Entity.Book;
import com.backend.librarymanagementsystem.Repository.AuthorRepository;
import com.backend.librarymanagementsystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    public BookResponseDto addBook(BookRequestDto bookRequestDto) {

        //getting the author
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        //creating the book object
        Book book = new Book();
        //setting the values
        book.setTitle(bookRequestDto.getTitle());
        book.setGenre(bookRequestDto.getGenre());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);

        //fetching the existing lists of books
        List<Book> booksWritten = author.getBooks();
        //updating the list
        booksWritten.add(book);

        //saving the author so the books added can get saved
        // remember when CRUD operation is done on parent child also get affected
        authorRepository.save(author);

        //creating response dto
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;
    }

    public List<BookResponseDto> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        BookResponseDto bookResponseDto;

        for (Book book: bookList){
            bookResponseDto = new BookResponseDto();
            bookResponseDto.setPrice(book.getPrice());
            bookResponseDto.setTitle(book.getTitle());


            bookResponseDtos.add(bookResponseDto);
        }
        return bookResponseDtos;
    }
}
