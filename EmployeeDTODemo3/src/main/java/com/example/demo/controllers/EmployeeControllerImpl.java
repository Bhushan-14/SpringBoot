package com.example.demo.controllers;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.EmployeeSearchDTO;
import com.example.demo.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequestDTO));
    }

    @Override
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(Integer id, EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, employeeRequestDTO));
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @Override
    public ResponseEntity<List<EmployeeResponseDTO>> searchEmployees(EmployeeSearchDTO searchDTO) {
        return ResponseEntity.ok(employeeService.searchEmployees(searchDTO));
    }
}
