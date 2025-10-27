package com.a2.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.a2.web.entities.CourseOfferingId;
import com.a2.web.entities.CourseOfferings;

import java.util.List;
public interface CourseOfferingsRepository extends JpaRepository<CourseOfferings, CourseOfferingId> {
    //creates a list of course offerings by the semester id given
    List<CourseOfferings> findAllById_SemesterID(Integer SemesterID);
}
