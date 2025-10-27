package com.a2.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.a2.web.entities.Course;
import java.util.List;

//course repository
public interface CourseRepository extends JpaRepository<Course, String> {
    
}

