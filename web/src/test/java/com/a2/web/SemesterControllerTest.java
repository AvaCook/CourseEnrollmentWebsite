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
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;

import com.a2.web.DTO.SemesterDTO;
import com.a2.web.controller.SemesterController;
import com.a2.web.entities.Semester;
import com.a2.web.repository.SemesterRepository;
import com.a2.web.service.SemesterService;


@ExtendWith(MockitoExtension.class)
public class SemesterControllerTest {
    
    @Mock
    private SemesterService semesterService;

    @InjectMocks
    private SemesterController semesterController;

    //testing controller with  mock service
    @Test
    void testGetOpenSemesters() {
        List<SemesterDTO> mockDTOs = List.of(new SemesterDTO(101, 1, 2025, true));
        when(semesterService.getOpenSemesters()).thenReturn(mockDTOs);

        ResponseEntity<List<SemesterDTO>> response = semesterController.getAllSemesters();

        assertEquals(1, response.getBody().size());
        assertEquals(101, response.getBody().get(0).getSemesterID());
        assertEquals(1, response.getBody().get(0).getSemester());
        assertEquals(2025, response.getBody().get(0).getYear());
        assertEquals(true, response.getBody().get(0).isOpenforenrolment());
    }
}