package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.DTO.IssueBookRequestDto;
import com.backend.librarymanagementsystem.DTO.IssueBookResponseDto;
import com.backend.librarymanagementsystem.Entity.Book;
import com.backend.librarymanagementsystem.Entity.LibraryCard;
import com.backend.librarymanagementsystem.Entity.Transaction;
import com.backend.librarymanagementsystem.Enum.CardStatus;
import com.backend.librarymanagementsystem.Enum.TransactionStatus;
import com.backend.librarymanagementsystem.Repository.BookRepository;
import com.backend.librarymanagementsystem.Repository.CardRepository;
import com.backend.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    //for automatic email
//    @Autowired
//    private JavaMailSender emailSender;

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto)throws Exception{

        //Creating transaction object
        Transaction transaction = new Transaction();
        //generate random no.
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        //get card and book object
        //if id not found it will return exception so try and catch
        LibraryCard card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            //function will return from here so I have to save the transaction
            transaction.setMessage("Invalid card ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid card ID");
        }

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            //function will return from here so I have to save the transaction
            transaction.setMessage("Invalid book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid book ID");
        }


        //both card and book are valid
        transaction.setBook(book);
        transaction.setCard(card);

        // * conditions to issue a book
        // 1--> card should not be expired
        // 2--> book should not be issued

        if(card.getStatus()!= CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            //function will return from here so I have to save the transaction
            transaction.setMessage("Your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated");
        }

        if (book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            //function will return from here so I have to save the transaction
            transaction.setMessage("Sorry! Book is already issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued");
        }

        //I can Issue the book
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Transaction success");

        book.setIssued(true);
        book.setCard(card);
        book.getTransaction().add(transaction);

        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        //card is parent of book and transaction
        //so just saving card entity will save book and transaction as well
        cardRepository.save(card);

        //creating response entity
        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(transaction.getTransactionStatus());


        //send an email automatically
//        String text = "thank you for choosing us!! Book "+ transaction.getBook().getTitle() + " issued successfully";
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo(card.getStudent().getEmail());
//        message.setSubject("Book successfully Issued");
//        message.setText(text);
//        emailSender.send(message);

        return issueBookResponseDto;
    }

    public String getAllTxns(int cardId) {
        List<Transaction> transactionList = transactionRepository.getAllSuccessfullTxnsWithCardId(cardId);
        String ans = "";
        for (Transaction transaction: transactionList){
            ans+= transaction.getTransactionNumber();
            ans+="\n";
        }
        return ans;
    }
}
