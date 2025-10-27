package com.a2.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.a2.web.controller.SemesterController;
import com.a2.web.entities.Semester;
import com.a2.web.entities.StudentCourseRegistration;
import com.a2.web.repository.SemesterRepository;
import com.a2.web.repository.StudentCourseRegistrationRepository;
import com.a2.web.controller.SemesterController;

import com.a2.web.controller.CoursesController;
import com.a2.web.entities.Semester;
import com.a2.web.repository.SemesterRepository;
import com.a2.web.controller.SemesterController;

@SpringBootApplication
public class WebApplication {
    private final SemesterController semesterController;
    private final CoursesController coursesController;

    WebApplication(SemesterController semesterController,CoursesController coursesController) {
        this.semesterController = semesterController;
        this.coursesController = coursesController;
    }

    
	public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
		
        /*
        ApplicationContext context = SpringApplication.run(WebApplication.class, args);

		var repository = context.getBean(StudentCourseRegistrationRepository.class);


		var stdCR = StudentCourseRegistration.builder()
                        .stdNo("c0002")
                        .courseID("MATH1110")
                        .semesterID(101)
                        .build();

        System.out.println(stdCR.toString());
        repository.save(stdCR);
    
       // int id = 1;

        */
       
	}

}
