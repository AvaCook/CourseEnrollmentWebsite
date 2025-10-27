package com.a2.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a2.web.DTO.CourseOfferingsDTO;
import com.a2.web.DTO.SemesterDTO;
import com.a2.web.entities.Course;
import com.a2.web.entities.Semester;
import com.a2.web.repository.SemesterRepository;
import com.a2.web.service.SemesterService;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


//add model, don't get all semester, get all open semesters

@RestController
@AllArgsConstructor
@RequestMapping("/unix")
public class SemesterController {

    private SemesterService semesterService;

    
    @GetMapping("/semesters")
    public ResponseEntity<List<SemesterDTO>> getAllSemesters() {

        //uses service
        var semList = semesterService.getOpenSemesters();


        //if any no open sems
        if (semList == null) {
            return new ResponseEntity<List<SemesterDTO>>(semList, HttpStatus.UNAUTHORIZED);
        }
        
        return new ResponseEntity<List<SemesterDTO>>(semList, HttpStatus.OK);
        
    }
}
