package com.a2.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.a2.web.DTO.CourseEnrollmentStatusDTO;
import com.a2.web.DTO.StudentCourseRegistrationDTO;
import com.a2.web.controller.CoursesController;
import com.a2.web.controller.StudentCourseRegistrationController;
import com.a2.web.entities.StudentCourseRegistration;
import com.a2.web.service.CourseService;
import com.a2.web.service.StudentCourseRegistrationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentCourseRegistrationControllerTest {
    @Mock
    private StudentCourseRegistrationService registrationService;

    @InjectMocks
    private StudentCourseRegistrationController registrationController;

    //test if a list of courses can be outputed using a student number
    @Test
    public void testGetCoursesByStudent() {
        String stdNo = "s123";
        List<StudentCourseRegistrationDTO> mockList = List.of(
                new StudentCourseRegistrationDTO("s123", "COMP1000", 101));

        when(registrationService.getRegistrationsByStudent(stdNo)).thenReturn(mockList);

        ResponseEntity<List<StudentCourseRegistrationDTO>> response = registrationController.getCoursesByStudent(stdNo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    //test if you can manually register a student
    @Test
    public void testCreateFixedRegistration() {
        doNothing().when(registrationService).createFixedRegistration();

        ResponseEntity<?> response = registrationController.createFixedStudentCourseRegistration();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //test if a student can be registured 
    /*
    @Test
    public void testRegisterCourse() {
        StudentCourseRegistrationDTO dto = new StudentCourseRegistrationDTO("s123", "COMP1000", 101);
        doNothing().when(registrationService).registerCourse(dto);

        ResponseEntity<?> response = registrationController.registerCourse(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    */
}
