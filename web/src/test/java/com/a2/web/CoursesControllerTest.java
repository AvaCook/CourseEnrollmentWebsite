package com.a2.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.a2.web.DTO.CourseEnrollmentStatusDTO;
import com.a2.web.controller.CoursesController;
import com.a2.web.service.CourseService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CoursesControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CoursesController coursesController;

    //test if a student is enrolled in a course
    @Test
    public void testGetCourseEnrollmentStatus() {
        String studentId = "S1234";
        int semesterId = 100;

        CourseEnrollmentStatusDTO courseStatus = new CourseEnrollmentStatusDTO("COMP101", "true");
        List<CourseEnrollmentStatusDTO> mockResult = List.of(courseStatus);

        when(courseService.getCourseEnrollmentStatus(studentId, semesterId)).thenReturn(mockResult);

        ResponseEntity<List<CourseEnrollmentStatusDTO>> response = coursesController.getCourseEnrollmentStatus(studentId, semesterId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("COMP101", response.getBody().get(0).getCourseID());
        assertEquals("true", response.getBody().get(0).getEnrolled());
    }

}
