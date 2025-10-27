package com.a2.web.controller;

import com.a2.web.DTO.CourseEnrollmentStatusDTO;
import com.a2.web.DTO.EnrollStudentRequest;
import com.a2.web.DTO.StudentCourseRegistrationDTO;
import com.a2.web.entities.StudentCourseRegistration;
import com.a2.web.service.StudentCourseRegistrationService;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/scr")
public class StudentCourseRegistrationController {

    private final StudentCourseRegistrationService scrService;

    
    //registers a student into a course for a given semester
    @PostMapping("/registrations")
    public ResponseEntity<CourseEnrollmentStatusDTO> registerCourse(@Valid @RequestBody EnrollStudentRequest dto) {
        
        CourseEnrollmentStatusDTO registered = scrService.registerCourse(dto);
        
        return new ResponseEntity<>(registered, HttpStatus.OK);
        
    }
    
    //finds all course registrations
    @RequestMapping("/sCR")
    public ResponseEntity<Iterable<StudentCourseRegistration>> getAllScr() {
       Iterable<StudentCourseRegistration> registrations = scrService.getAllRegistrations();
        if (registrations == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    //gets the courses a student is registured for
    @GetMapping("/{stdNo}")
    public ResponseEntity<List<StudentCourseRegistrationDTO>> getCoursesByStudent(@PathVariable String stdNo) {
        List<StudentCourseRegistrationDTO> result = scrService.getRegistrationsByStudent(stdNo);
       if (result == null || result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //manualy registures a student in a course
    @PostMapping("/register/{semester}/{courses}")
    public ResponseEntity<?> createFixedStudentCourseRegistration() {
        try {
            scrService.createFixedRegistration();
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
