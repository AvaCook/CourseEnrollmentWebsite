package com.a2.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2.web.DTO.SemesterDTO;
import com.a2.web.repository.SemesterRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SemesterService {

    @Autowired
    private final SemesterRepository semesterRepository;

    public List<SemesterDTO> getOpenSemesters(){
        //put all sems into a list with dto
        List<SemesterDTO> semList = semesterRepository.findAll()
                            .stream()
                            .map(Semester -> new SemesterDTO(Semester.getSemesterID(), Semester.getSemester(), Semester.getYear(), Semester.isOpenforenrolment()))
                            .toList();
        if (semList.isEmpty()){
            return null;
        }
        
        //filters for open sems
        semList = new ArrayList<>(semList);
        semList.removeIf(sem -> !sem.isOpenforenrolment());

        return semList;                    
    }
}
