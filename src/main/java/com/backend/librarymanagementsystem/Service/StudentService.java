package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.Entity.LibraryCard;
import com.backend.librarymanagementsystem.Entity.Student;
import com.backend.librarymanagementsystem.Enum.CardStatus;
import com.backend.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student){

        //set the value of card because we want to generate card automatically
        LibraryCard card =  new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setValidTill("03/2025");
        card.setStudent(student);

        //set the card attribute in student
        student.setCard(card);

        //we don't have to save card as cascade will automatically save card for us by calling its repo
        //if we call parent-child will save automatically
        //but visa-versa is not true
        //as we have written cascade in parent class only
        studentRepository.save(student);
    }
}