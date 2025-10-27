package com.a2.web.controller;

import com.a2.web.DTO.CourseDTO;
import com.a2.web.DTO.CourseEnrollmentStatusDTO;
import com.a2.web.DTO.CourseOfferingsDTO;
import com.a2.web.entities.Course;
import com.a2.web.entities.CourseOfferings;
import com.a2.web.service.CourseService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService courseService;

    //gets all courses from the course table
    @GetMapping
    public ResponseEntity<Iterable<Course>> getAllCourses() {
        var courses = courseService.getAllCourse();
        if (courses == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //gets all courses from the course offerings table
    @GetMapping("/offer")
     public ResponseEntity<Iterable<CourseOfferings>> getAllCourseOfferings() {
        var offerings = courseService.getAllCourseOfferings();
        if (offerings == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offerings, HttpStatus.OK);
    }

    //gets a all courses from the course offerings table using the DTO
    @GetMapping("/offerdto")
   public ResponseEntity<List<CourseOfferingsDTO>> getAllCourseOfferingsDto() {
        var offerings = courseService.getAllCourseOfferingsDTO();
        if (offerings == null || offerings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offerings, HttpStatus.OK);
    }

    //gets all courses in the semester that is chosen
    @GetMapping("/{SemesterID}")
     public ResponseEntity<List<CourseOfferingsDTO>> getCoursesBySemester(@PathVariable int SemesterID) {
        var courses = courseService.getCoursesBySemester(SemesterID);
        if (courses == null || courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //gets all the courses using the courseDTO using athentification
    @RequestMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCoursesAuth(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "param1") String parameter
    ) {
        var courses = courseService.getAllCoursesAuth(authToken, parameter);
        if (courses == null || courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //get the courses in a specific semester and checks if a student is enrolled in them or not
    @GetMapping("/{studentId}/{semesterId}")
    public ResponseEntity<List<CourseEnrollmentStatusDTO>> getCourseEnrollmentStatus(
            @PathVariable String studentId,
            @PathVariable int semesterId) {
        var result = courseService.getCourseEnrollmentStatus(studentId, semesterId);
        if (result == null || result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
