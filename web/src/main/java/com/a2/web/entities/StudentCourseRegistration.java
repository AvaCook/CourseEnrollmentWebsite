package com.a2.web.entities;

import java.math.BigDecimal;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.Valid;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "studentcourseregistration")

//creates student course registration from the student course registration table
public class StudentCourseRegistration {
    
    @EmbeddedId
    private StudentCourseRegistrationId id;

    @ManyToOne
    @MapsId("stdNo")
    @JoinColumn(name = "stdno")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @MapsId("courseID")
    @JoinColumn(name = "courseID")
    @JsonIgnore
    private Course course;

    @ManyToOne
    @MapsId("semesterID")
    @JoinColumn(name = "semesterID")
    @JsonIgnore
    private Semester semester;

    @Column(name = "grade")
    private String grade;

    @Column(name = "mark")
    private String mark;

}