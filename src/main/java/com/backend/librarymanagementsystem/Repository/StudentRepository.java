package com.backend.librarymanagementsystem.Repository;

import com.backend.librarymanagementsystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByEmail(String email);

    List<Student> findByName(String name);

    List<Student> findByAge(int age);
}
