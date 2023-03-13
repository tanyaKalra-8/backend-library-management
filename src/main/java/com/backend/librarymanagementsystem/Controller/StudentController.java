package com.backend.librarymanagementsystem.Controller;

import com.backend.librarymanagementsystem.Entity.Student;
import com.backend.librarymanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return "Student has been added";
    }

    @GetMapping("/get_students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    //get all students by name
    @GetMapping("/get_student_name")
    public List<Student> getStudent(@RequestParam("name") String name){
        return studentService.getStudent(name);
    }
}
