package com.backend.librarymanagementsystem.Entity;

import com.backend.librarymanagementsystem.Enum.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor //default constructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String validTill;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    //student card relation

    @OneToOne
    @JoinColumn
    @JsonIgnore
    Student student;
}
