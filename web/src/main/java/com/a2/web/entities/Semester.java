package com.a2.web.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Builder
@ToString
@Entity
@Table(name = "Semester")
public class Semester {

    @Id
    @Column(name = "semesterID")
    private int semesterID;

    @Column(name = "semester")
    private int semester;

    @Column(name = "year")
    private int year;

    @Column(name = "openforenrolment")
    private boolean openforenrolment;

    @OneToMany(mappedBy = "semester")
    @Builder.Default
    @JsonIgnore
    private List<CourseOfferings> courseOfferings = new ArrayList<>();

    public Semester(int semester, int year, boolean openforenrolment){
        this.semester = semester;
        this.year = year;
        this.openforenrolment = openforenrolment;
    }
}