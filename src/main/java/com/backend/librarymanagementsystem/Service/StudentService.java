package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.DTO.AllStudentsResponseDto;
import com.backend.librarymanagementsystem.DTO.StudentRequestDto;
import com.backend.librarymanagementsystem.DTO.StudentResponseDto;
import com.backend.librarymanagementsystem.DTO.StudentUpdateEmailRequestDto;
import com.backend.librarymanagementsystem.Entity.LibraryCard;
import com.backend.librarymanagementsystem.Entity.Student;
import com.backend.librarymanagementsystem.Enum.CardStatus;
import com.backend.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void addStudent(StudentRequestDto studentRequestDto){

        //create a student object
        Student student =  new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());


        //set the value of card because we want to generate card automatically
        //creating a card object
        LibraryCard card =  new LibraryCard();
        card.setStatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        //set the card attribute in student
        student.setCard(card);

        //we don't have to save card as cascade will automatically save card for us by calling its repo
        //if we call parent-child will save automatically
        //but visa-versa is not true
        //as we have written cascade in parent class only
        studentRepository.save(student);
    }

    public List<AllStudentsResponseDto> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<AllStudentsResponseDto> allStudentsResponseDtos = new ArrayList<>();
        AllStudentsResponseDto allStudentsResponseDto;

        for (Student student: studentList){
            allStudentsResponseDto = new AllStudentsResponseDto();
            allStudentsResponseDto.setName(student.getName());
            allStudentsResponseDto.setAge(student.getAge());
            allStudentsResponseDto.setDepartment(student.getDepartment());
            allStudentsResponseDto.setEmail(student.getEmail());
            allStudentsResponseDto.setCard(student.getCard());

            allStudentsResponseDtos.add(allStudentsResponseDto);
        }
        return allStudentsResponseDtos;
    }

    public List<Student> getStudent(String name) {
        return studentRepository.findByName(name);
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public List<Student> getStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) {

        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        //update step
        Student updatedStudent = studentRepository.save(student);

        //convert updated student to DTO
        //creating new Dto response object
        StudentResponseDto studentResponseDto =  new StudentResponseDto();

        //setting the values
        studentResponseDto.setId(updatedStudent.getId());
        studentResponseDto.setName(updatedStudent.getName());
        studentResponseDto.setEmail(updatedStudent.getEmail());

        //returning the updated dta
        return studentResponseDto;
    }
}
