package com.backend.librarymanagementsystem.DTO;

import com.backend.librarymanagementsystem.Entity.LibraryCard;
import com.backend.librarymanagementsystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetStudentsResponseDto {

    private String name;
    private int age;
    private Department department;
    private String email;
    private LibraryCard card;
}
