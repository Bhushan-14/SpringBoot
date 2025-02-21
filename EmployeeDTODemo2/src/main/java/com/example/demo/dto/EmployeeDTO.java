package com.example.demo.dto;

import com.example.demo.dao.entities.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Integer employeeId;
    private String name;
    private Integer age;
    private String email;
    private Boolean isActive;
    private Boolean isDeleted;
    private AuditEntity auditEntity;
}
