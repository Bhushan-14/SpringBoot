package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    private String country;
    private String state;
    private String district;
    private String city;
    private String street;
    private String pincode;
    private Set<DepartmentDTO> departments; 
}
