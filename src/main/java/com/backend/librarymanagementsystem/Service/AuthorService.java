package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.DTO.AuthorRequestDto;
import com.backend.librarymanagementsystem.DTO.AuthorResponseDto;
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

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto){

        //creating the author object
        Author author =  new Author();
        //setting the value
        author.setName(authorRequestDto.getName());
        author.setAge(authorRequestDto.getAge());
        author.setMobNo(authorRequestDto.getMobNo());
        author.setEmail(authorRequestDto.getEmail());

        // book will be saved by author only
        authorRepository.save(author);

        //creating response dto object
        AuthorResponseDto authorResponseDto =  new AuthorResponseDto();
        //setting the values
        authorResponseDto.setId(author.getId());
        authorResponseDto.setName(author.getName());
        authorResponseDto.setEmail(author.getEmail());

        return authorResponseDto;
    }

    public List<Author> getAuthors() {

        return authorRepository.findAll();
    }
}
