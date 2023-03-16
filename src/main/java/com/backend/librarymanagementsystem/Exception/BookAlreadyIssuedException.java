package com.backend.librarymanagementsystem.Exception;

public class BookAlreadyIssuedException extends Exception{

    public BookAlreadyIssuedException(String message){
        super(message);
    }
}
