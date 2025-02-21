package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    
    private Integer employeeId;
    private String name;
    private Integer age;
    private String email;
    private Boolean isActive;
}
