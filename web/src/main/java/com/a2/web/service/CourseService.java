package com.a2.web.service;

import com.a2.web.DTO.CourseDTO;
import com.a2.web.DTO.CourseEnrollmentStatusDTO;
import com.a2.web.DTO.CourseOfferingsDTO;
import com.a2.web.entities.Course;
import com.a2.web.entities.CourseOfferings;
import com.a2.web.entities.StudentCourseRegistration;
import com.a2.web.repository.CourseOfferingsRepository;
import com.a2.web.repository.CourseRepository;
import com.a2.web.repository.StudentCourseRegistrationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseOfferingsRepository courseOfferingsRepository;
    private final StudentCourseRegistrationRepository studentCourseRegistrationRepository;

    //get all courses using athentification
    public List<CourseDTO> getAllCoursesAuth(String authToken, String parameter) {
        System.out.println("Authentication token: " + authToken);
        System.out.println("Parameter passed: " + parameter);
        List<Course> courseList = courseRepository.findAll();
        if (courseList.isEmpty()) {
            return null;
        }
        return courseList.stream()
                .map(course -> new CourseDTO(course.getCourseID(), course.getCName(), course.getCredits()))
                .toList();
    }
    //gets all courses in course offering using the dto
    public List<CourseOfferingsDTO> getAllCourseOfferingsDTO() {
        List<CourseOfferings> offerings = courseOfferingsRepository.findAll();
        if (offerings.isEmpty()) {
            return null;
        }

        return offerings.stream()
                .map(c -> new CourseOfferingsDTO(c.getSemester().getSemesterID(), c.getCourse().getCourseID()))
                .toList();
    }
    //gets a list of all courses by semester given
    public List<CourseOfferingsDTO> getCoursesBySemester(int semesterID) {
        List<CourseOfferings> offerings = courseOfferingsRepository.findAllById_SemesterID(semesterID);
        if (offerings.isEmpty()) {
            return null;
        }

        return offerings.stream()
                .map(c -> new CourseOfferingsDTO(c.getSemester().getSemesterID(), c.getCourse().getCourseID()))
                .toList();
    }
    //gets a list of course enrollment status using student id and semester id to return the courses and if they are enrolled or not
    public List<CourseEnrollmentStatusDTO> getCourseEnrollmentStatus(String studentId, int semesterId) {
        List<CourseOfferings> offeredCourses = courseOfferingsRepository.findAllById_SemesterID(semesterId);
            if (offeredCourses.isEmpty()) {
                return null;
            }
        List<StudentCourseRegistration> registrations = studentCourseRegistrationRepository
                .findByStudent_StdNoAndSemester_SemesterID(studentId, semesterId);

        Set<String> enrolledCourseIDs = registrations.stream()
                .map(reg -> reg.getCourse().getCourseID())
                .collect(Collectors.toSet());

        return offeredCourses.stream()
                .map(co -> new CourseEnrollmentStatusDTO(
                        co.getCourse().getCourseID(),
                        String.valueOf(enrolledCourseIDs.contains(co.getCourse().getCourseID()))
                ))
                .toList();
    }
    //gets all courses using courses
    public Iterable<Course> getAllCourse() {
        Iterable<Course> courses = courseRepository.findAll();
        if (!courses.iterator().hasNext()) {
            return null;
        }
        return courses;
    }
    //gets all courses using course offerings
    public Iterable<CourseOfferings> getAllCourseOfferings() {
         Iterable<CourseOfferings> offerings = courseOfferingsRepository.findAll();
        if (!offerings.iterator().hasNext()) {
            return null;
        }
        return offerings;
    }
}