package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.Entity.Author;
import com.backend.librarymanagementsystem.Entity.Book;
import com.backend.librarymanagementsystem.Repository.AuthorRepository;
import com.backend.librarymanagementsystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;


    public String addBook(Book book) throws Exception {

        Author author;

        try {
            author = authorRepository.findById(book.getAuthor().getId()).get();
        }
        catch (Exception e){
            return "Author not added "+ e.getMessage();
        }

        List<Book> booksWritten = author.getBooks();
        booksWritten.add(book);

        //saving the author so the books added can get saved
        // remember when CRUD operation is done on parent child also get affected
        authorRepository.save(author);
        return "Author added successfully";
    }
}
