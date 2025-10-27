package com.a2.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a2.web.DTO.StudentDTO;
import com.a2.web.entities.Student;
import com.a2.web.repository.StudentRepository;
import com.a2.web.service.StudentService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//for testing only
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;
    
    
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id)
    {
         return studentRepository.findById(id).orElse(null);

    }

    @PostMapping("/add")
    public void addStudent(){
        String stdNo = "c0001";
        String givenNames = "Peter";
        String lastName = "Brown";
        String password = "password";
        StudentService studentService = new StudentService(studentRepository);
        studentService.addStudent(stdNo, givenNames, lastName, password);
    }
}
