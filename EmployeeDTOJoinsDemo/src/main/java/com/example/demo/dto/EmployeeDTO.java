package com.example.demo.dto;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private boolean isActive;
    private boolean isDeleted;
    private Set<Long> departmentIds; // Store only department IDs
}
