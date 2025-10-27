package com.a2.web.DTO;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequestDto {
    
    @NotBlank (message = "Student number cannot be blank")
    private String studentNo;

    @NotBlank (message = "Password number cannot be blank")
    private String password;
}
