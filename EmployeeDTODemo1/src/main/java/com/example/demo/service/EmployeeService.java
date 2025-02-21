package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.response.EmployeeApiResponse;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    EmployeeApiResponse getEmployeeById(Integer id);
    EmployeeApiResponse getAllEmployees(Pageable pageable);
    EmployeeApiResponse createEmployee(EmployeeDTO dto);
    EmployeeApiResponse updateEmployee(Integer id, EmployeeDTO dto);
    EmployeeApiResponse deleteEmployee(Integer id);
    EmployeeApiResponse findEmployeesWithPagination(Pageable pageable);
}
