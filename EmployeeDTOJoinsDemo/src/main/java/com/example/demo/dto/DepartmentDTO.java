package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isDeleted;
    private Set<Long> employeeIds; // Ensure this is initialized safely in your service layer
}
