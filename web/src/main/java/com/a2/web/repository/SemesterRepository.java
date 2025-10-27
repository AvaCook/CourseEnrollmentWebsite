package com.a2.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.a2.web.entities.Semester;

//resposity for semester entity
public interface SemesterRepository extends JpaRepository<Semester, Integer>{

}
