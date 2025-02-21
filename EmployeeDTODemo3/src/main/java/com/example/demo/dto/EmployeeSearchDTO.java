package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeSearchDTO {
    private Integer employeeId;
    private String name;
    private String email;
    private Integer age;
    private Boolean isActive;
}
