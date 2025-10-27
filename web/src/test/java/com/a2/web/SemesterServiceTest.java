package com.a2.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.a2.web.DTO.SemesterDTO;
import com.a2.web.controller.SemesterController;
import com.a2.web.entities.Semester;
import com.a2.web.repository.SemesterRepository;
import com.a2.web.service.SemesterService;


@ExtendWith(MockitoExtension.class)
public class SemesterServiceTest {
    
    @InjectMocks
    private SemesterService semesterService;

    @Mock
    private SemesterRepository semesterRepository;

    //testing getting semester from database
    @Test
    void testGetOpenSemesters() {
        List<Semester> mockDTOs = List.of(new Semester(1, 2025, true));
        when(semesterRepository.findAll()).thenReturn(mockDTOs);

        List<SemesterDTO> response = semesterService.getOpenSemesters();

        assertEquals(1, response.get(0).getSemester());
        assertEquals(2025, response.get(0).getYear());
        assertEquals(true, response.get(0).isOpenforenrolment());
    }
}