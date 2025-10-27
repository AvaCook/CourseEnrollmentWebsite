package com.a2.web.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//finds the semesterID and courseID to help merge course offering and courses table
public class CourseOfferingId implements Serializable {
    
    @Column(name = "semesterID")
    private Integer semesterID;
    
    @Column(name = "courseID")
    private String courseID;

    
}