package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.Entity.Author;
import com.backend.librarymanagementsystem.Entity.Book;
import com.backend.librarymanagementsystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public void addAuthor(Author author){

        // book will be saved by author only
        authorRepository.save(author);
    }

    public List<Author> getAuthors() {

        return authorRepository.findAll();
    }
}
