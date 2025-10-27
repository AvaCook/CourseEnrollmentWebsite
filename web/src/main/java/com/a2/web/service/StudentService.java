package com.a2.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.a2.web.DTO.StudentDTO;
import com.a2.web.entities.Student;
import com.a2.web.repository.StudentRepository;
import com.a2.web.security.PasswordSecurity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentService {
    
    @Autowired
    private final StudentRepository studentRepository;
        


    public StudentDTO authenticateStudent(String stdNo, String password)
    {
        //gets student by stdno
        var student = studentRepository.findById(stdNo).orElse(null);
        //if student found, check if pword is right with security
        if (student!=null)
        {
            PasswordSecurity pSec = new PasswordSecurity();
            if (pSec.verifyPassword(password, student))
                return new StudentDTO(student.getStdNo(), student.getLastname(), student.getGivenNames());

        }
        return null;
    }

    //add students for test
    public void addStudent(String stdNo, String givenNames, String lastName, String password)
    {
        // Generate salt and password hash
        PasswordSecurity pSec = new PasswordSecurity();
        Double salt = pSec.generateSalt();
        String passwordHash = pSec.hashPassword(password, salt);

        // Create student object and add to database
        Student student  = new Student(stdNo, givenNames, lastName, passwordHash, salt);
        System.out.println(student.toString());
        studentRepository.save(student);

    }
}
