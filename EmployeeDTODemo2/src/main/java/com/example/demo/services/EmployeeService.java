package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.responses.EmployeeApiResponse;

@Service
public interface EmployeeService {
    EmployeeApiResponse getEmployeeById(Integer id);
    EmployeeApiResponse getAllEmployees();
    EmployeeApiResponse addEmployee(EmployeeDTO employeeDto);
    EmployeeApiResponse deleteEmployeeById(Integer id);
    EmployeeApiResponse updateEmployee(Integer id, EmployeeDTO employeeDto);
    EmployeeApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);
}
