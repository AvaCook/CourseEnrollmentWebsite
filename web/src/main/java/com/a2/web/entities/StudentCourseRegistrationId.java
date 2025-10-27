package com.a2.web.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

//creates the student number, course id, and semester id to help register a student
public class StudentCourseRegistrationId implements Serializable {
    
    @Column(name = "stdno")
    private String stdNo;

    @Column(name = "courseID")
    private String courseID;

    @Column(name = "semesterID")
    private Integer semesterID;

    // equals and hashCode
}
