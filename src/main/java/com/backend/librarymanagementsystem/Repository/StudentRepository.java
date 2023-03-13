package com.backend.librarymanagementsystem.Repository;

import com.backend.librarymanagementsystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
