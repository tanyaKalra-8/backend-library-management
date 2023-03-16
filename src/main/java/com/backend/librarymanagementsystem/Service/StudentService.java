package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.DTO.StudentResponseDto;
import com.backend.librarymanagementsystem.DTO.StudentRequestDto;
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

    public List<StudentResponseDto> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        StudentResponseDto studentResponseDto;

        for (Student student: studentList){
            studentResponseDto = new StudentResponseDto();
            studentResponseDto.setName(student.getName());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setEmail(student.getEmail());
            studentResponseDto.setCard(student.getCard());

            studentResponseDtos.add(studentResponseDto);
        }
        return studentResponseDtos;
    }

    public List<StudentResponseDto> getStudent(String name) {
        List<Student> studentList= studentRepository.findByName(name);
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        StudentResponseDto studentResponseDto;

        for (Student student: studentList){
            studentResponseDto = new StudentResponseDto();
            studentResponseDto.setName(student.getName());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setEmail(student.getEmail());
            studentResponseDto.setCard(student.getCard());

            studentResponseDtos.add(studentResponseDto);
        }
        return studentResponseDtos;
    }

    public StudentResponseDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setCard(student.getCard());

        return studentResponseDto;
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public List<StudentResponseDto> getStudentByAge(int age) {
        List<Student> studentList = studentRepository.findByAge(age);
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        StudentResponseDto studentResponseDto;

        for (Student student: studentList){
            studentResponseDto = new StudentResponseDto();
            studentResponseDto.setName(student.getName());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setEmail(student.getEmail());
            studentResponseDto.setCard(student.getCard());

            studentResponseDtos.add(studentResponseDto);
        }
        return studentResponseDtos;
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
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setCard(student.getCard());

        //returning the updated dta
        return studentResponseDto;
    }
}
