package com.a2.web.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "stdno")
    private String stdNo;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "givennames")
    private String givenNames;

    @Column(name = "passwordhash")
    private String passwordHash;
    
    @Column(name = "passwordsalt")
    private Double passwordSalt;


}