package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees(); 
    Optional<EmployeeDTO> getEmployeeById(Long empId);
    EmployeeDTO insertEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO); 
    void softDeleteEmployee(Long empId);
}
