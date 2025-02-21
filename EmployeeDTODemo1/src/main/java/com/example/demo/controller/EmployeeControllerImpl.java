package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.response.EmployeeApiResponse;
import com.example.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/employees") 
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<EmployeeApiResponse> getEmployeeById(Integer employeeId) {
        log.info("Fetching Employee by ID: {}", employeeId);
        EmployeeApiResponse response = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> createEmployee(EmployeeDTO employeeDto) {
        log.info("Creating new Employee");
        EmployeeApiResponse response = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> updateEmployee(Integer employeeId, EmployeeDTO employeeDto) {
        log.info("Updating Employee with ID: {}", employeeId);
        EmployeeApiResponse response = employeeService.updateEmployee(employeeId, employeeDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> deleteEmployee(Integer employeeId) {
        log.info("Deleting Employee with ID: {}", employeeId);
        EmployeeApiResponse response = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> getAllEmployees(int page, int size) {
        log.info("Fetching all Employees - Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        EmployeeApiResponse response = employeeService.getAllEmployees(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> getEmployeesWithPagination(Pageable pageable) {
        log.info("Fetching Employees with Pagination");
        EmployeeApiResponse response = employeeService.findEmployeesWithPagination(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
