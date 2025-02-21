package com.example.demo.dto;

import com.example.demo.dao.entities.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private boolean isActive; 
    private AuditEntity auditEntity; 
}