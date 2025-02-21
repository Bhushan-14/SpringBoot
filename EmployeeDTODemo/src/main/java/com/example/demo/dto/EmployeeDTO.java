package com.example.demo.dto;

import lombok.*;
import java.time.LocalDate;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private Double salary;
    private LocalDate dateOfJoining;
    private Boolean deleted;
}
