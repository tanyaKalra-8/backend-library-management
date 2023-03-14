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

    @GetMapping("/get_student_email")
    public Student getStudentByEmail(@RequestParam("email") String email){
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/get_student_by_age")
    public List<Student> getStudentByAge(@RequestParam("age") int age){
        return studentService.getStudentByAge(age);
    }

    @DeleteMapping("/delete_student_by_id")
    public String deleteStudentById(@RequestParam("id") int id){
        studentService.deleteStudentById(id);
        return "Student deleted successfully";
    }

    @DeleteMapping("delete_all")
    public String deleteAll(){
        studentService.deleteAll();
        return "All students deleted successfully";
    }
}
