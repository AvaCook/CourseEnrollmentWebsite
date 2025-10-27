package com.a2.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

//gets the semester id and course id 
public class CourseOfferingsDTO {
    
    private int semesterID;
    private String courseID;
}
