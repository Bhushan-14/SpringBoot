package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.response.EmployeeApiResponse;

public interface EmployeeService {
    EmployeeApiResponse getEmployeeById(Long id);
    EmployeeApiResponse getAllEmployees();
    EmployeeApiResponse createEmployee(EmployeeDTO employeeDTO);
    EmployeeApiResponse updateEmployee(Long id, EmployeeDTO employeeDTO);
    EmployeeApiResponse deleteEmployee(Long id);
}
