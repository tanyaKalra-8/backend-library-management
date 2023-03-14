package com.backend.librarymanagementsystem.Entity;

import com.backend.librarymanagementsystem.Enum.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor //default constructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updationDate;

    //student card relation

    @OneToOne
    @JoinColumn
    @JsonIgnore
    Student student;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    List<Transaction> transactionList = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    List<Book> booksIssued = new ArrayList<>();
}
