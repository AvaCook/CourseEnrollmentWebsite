package com.a2.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

//EnrollStudentRequest is being used instead 
public class StudentCourseRegistrationDTO {
    private String stdNo;
    private String courseID;
    private int semesterID;
    
}
