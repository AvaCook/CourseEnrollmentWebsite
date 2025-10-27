package com.a2.web.service;

import com.a2.web.DTO.CourseEnrollmentStatusDTO;
import com.a2.web.DTO.EnrollStudentRequest;
import com.a2.web.DTO.StudentCourseRegistrationDTO;
import com.a2.web.entities.*;
import com.a2.web.repository.CourseRepository;
import com.a2.web.repository.SemesterRepository;
import com.a2.web.repository.StudentCourseRegistrationRepository;
import com.a2.web.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentCourseRegistrationService {

    private final StudentCourseRegistrationRepository studentCourseRegistrationRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;

    //finds all student course registrations
    public Iterable<StudentCourseRegistration> getAllRegistrations() {
        return studentCourseRegistrationRepository.findAll();
    }
    //makes a list of registrations by student number that is given
    public List<StudentCourseRegistrationDTO> getRegistrationsByStudent(String stdNo) {
        List<StudentCourseRegistration> registrations = studentCourseRegistrationRepository.findAllById_StdNo(stdNo);
        if (registrations.isEmpty()) {
            return null;
        }
        return registrations.stream()
                .map(c -> new StudentCourseRegistrationDTO(
                        c.getStudent().getStdNo(),
                        c.getCourse().getCourseID(),
                        c.getSemester().getSemesterID()))
                .toList();
    }
    //manually registures a student for a course
    public void createFixedRegistration() {
        String stdNo = "c0001";
        String courseID = "COMP1140";
        int semesterID = 101;

        Student student = studentRepository.findById(stdNo)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseID)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Semester semester = semesterRepository.findById(semesterID)
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        StudentCourseRegistrationId id = new StudentCourseRegistrationId(stdNo, courseID, semesterID);
        StudentCourseRegistration registration = new StudentCourseRegistration(id, student, course, semester, null, null);
        studentCourseRegistrationRepository.save(registration);
    }
    //registures a student for a course using the dto
    public CourseEnrollmentStatusDTO registerCourse(EnrollStudentRequest dto) {

        Student student = studentRepository.findById(dto.getStdNo())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(dto.getCourseID())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Semester semester = semesterRepository.findById(dto.getSemesterID())
                .orElseThrow(() -> new RuntimeException("Semester not found"));

        
        StudentCourseRegistrationId id = new StudentCourseRegistrationId(dto.getStdNo(), dto.getCourseID(), dto.getSemesterID());
        StudentCourseRegistration registration = new StudentCourseRegistration(id, student, course, semester, null, null);

        //check that isn't already enrolled
        var studentEnrolled = studentCourseRegistrationRepository.findByStudent_StdNoAndCourse_CourseIDAndSemester_SemesterID(dto.getStdNo(), dto.getCourseID(), dto.getSemesterID());
        if (studentEnrolled == null){
                studentCourseRegistrationRepository.save(registration);
                return new CourseEnrollmentStatusDTO("Enrolled", "true");
        }
        else{
                return new CourseEnrollmentStatusDTO("Didn't enrol", "false");
                
        }
    }
}
