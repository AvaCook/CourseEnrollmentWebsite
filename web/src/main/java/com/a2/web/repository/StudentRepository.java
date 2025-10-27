package com.a2.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.a2.web.entities.Student;

//reposity for student entity
public interface StudentRepository extends JpaRepository<Student, String>{

}
