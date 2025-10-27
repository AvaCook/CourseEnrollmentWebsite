package com.a2.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SemesterDTO {

    private int SemesterID;
    private int Semester;
    private int year;
    private boolean openforenrolment;
    
}
