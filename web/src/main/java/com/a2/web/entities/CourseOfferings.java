package com.a2.web.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "courseofferings")

//creates course offerings from the course offerings table
public class CourseOfferings {

    @EmbeddedId
    private CourseOfferingId id;

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

    @Column(name = "maxcapacity")
    private int maxCapacity;
}
