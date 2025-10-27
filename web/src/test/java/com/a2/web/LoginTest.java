package com.a2.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.a2.web.DTO.StudentDTO;
import com.a2.web.entities.Student;
import com.a2.web.repository.StudentRepository;
import com.a2.web.service.StudentService;


@ExtendWith(MockitoExtension.class)
public class LoginTest {
    
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    //testing if a validation when valid student logs in
    @Test
    void testLoginStudent() {
        when(studentRepository.findById("c0001")).thenReturn(Optional.of(new Student("c0001", "Brown", "Charlie", "$argon2id$v=19$m=15,t=2,p=1$MC4wMjY1NTUyODcxNjI5ODQyNzY$8b3B2a5pXd74uppnr/zmrcGxxNzjbZ+NgvIZtegKImg", 0.026555287162984276)));

        StudentDTO response = studentService.authenticateStudent("c0001", "password" );

        assertEquals("c0001", response.getStdNo());
        assertEquals("Brown", response.getLastname());

    }
}