package com.a2.web.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

//validation for registering a student
public class EnrollStudentRequest {

    @NotBlank(message = "Student number is required")
    private String stdNo;

    @NotBlank(message = "Course ID is required")
    private String courseID;

    @NotNull(message = "Semester ID is required")
    private Integer semesterID;
}
