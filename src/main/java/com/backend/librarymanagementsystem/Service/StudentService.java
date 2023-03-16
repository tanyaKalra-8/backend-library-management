package com.backend.librarymanagementsystem.Service;

import com.backend.librarymanagementsystem.DTO.GetStudentsResponseDto;
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

    public List<GetStudentsResponseDto> getStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<GetStudentsResponseDto> getStudentsResponseDtos = new ArrayList<>();
        GetStudentsResponseDto getStudentsResponseDto;

        for (Student student: studentList){
            getStudentsResponseDto = new GetStudentsResponseDto();
            getStudentsResponseDto.setName(student.getName());
            getStudentsResponseDto.setAge(student.getAge());
            getStudentsResponseDto.setDepartment(student.getDepartment());
            getStudentsResponseDto.setEmail(student.getEmail());
            getStudentsResponseDto.setCard(student.getCard());

            getStudentsResponseDtos.add(getStudentsResponseDto);
        }
        return getStudentsResponseDtos;
    }

    public List<GetStudentsResponseDto> getStudent(String name) {
        List<Student> studentList= studentRepository.findByName(name);
        List<GetStudentsResponseDto> getStudentsResponseDtos = new ArrayList<>();
        GetStudentsResponseDto getStudentsResponseDto;

        for (Student student: studentList){
            getStudentsResponseDto = new GetStudentsResponseDto();
            getStudentsResponseDto.setName(student.getName());
            getStudentsResponseDto.setAge(student.getAge());
            getStudentsResponseDto.setDepartment(student.getDepartment());
            getStudentsResponseDto.setEmail(student.getEmail());
            getStudentsResponseDto.setCard(student.getCard());

            getStudentsResponseDtos.add(getStudentsResponseDto);
        }
        return getStudentsResponseDtos;
    }

    public GetStudentsResponseDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);

        GetStudentsResponseDto getStudentsResponseDto = new GetStudentsResponseDto();
        getStudentsResponseDto.setName(student.getName());
        getStudentsResponseDto.setAge(student.getAge());
        getStudentsResponseDto.setDepartment(student.getDepartment());
        getStudentsResponseDto.setEmail(student.getEmail());
        getStudentsResponseDto.setCard(student.getCard());

        return getStudentsResponseDto;
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }

    public List<GetStudentsResponseDto> getStudentByAge(int age) {
        List<Student> studentList = studentRepository.findByAge(age);
        List<GetStudentsResponseDto> getStudentsResponseDtos = new ArrayList<>();
        GetStudentsResponseDto getStudentsResponseDto;

        for (Student student: studentList){
            getStudentsResponseDto = new GetStudentsResponseDto();
            getStudentsResponseDto.setName(student.getName());
            getStudentsResponseDto.setAge(student.getAge());
            getStudentsResponseDto.setDepartment(student.getDepartment());
            getStudentsResponseDto.setEmail(student.getEmail());
            getStudentsResponseDto.setCard(student.getCard());

            getStudentsResponseDtos.add(getStudentsResponseDto);
        }
        return getStudentsResponseDtos;
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
