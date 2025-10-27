package com.a2.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a2.web.entities.StudentCourseRegistration;
import com.a2.web.entities.StudentCourseRegistrationId;

public interface StudentCourseRegistrationRepository extends JpaRepository<StudentCourseRegistration, StudentCourseRegistrationId> {
    
    //List<StudentCourseRegistration> findAllByStdNo(String stdNo);
    //makes a list of students who are registered by student id
    List<StudentCourseRegistration> findAllById_StdNo(String stdNo);
    //makes a list of students using the student number and semester id
    List<StudentCourseRegistration> findByStudent_StdNoAndSemester_SemesterID(String stdNo, int semesterId);
    // holds a value to find if a student is registured in a course or not
    StudentCourseRegistration findByStudent_StdNoAndCourse_CourseIDAndSemester_SemesterID(String stdNo, String courseID, int semesterID);

}
