package com.a2.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

//gets the course id and enrollment status
public class CourseEnrollmentStatusDTO {
    private String courseID;
    private String enrolled;
}