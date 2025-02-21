package com.example.demo.controllers;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.EmployeeSearchDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employees")
public interface EmployeeController {

    @GetMapping("/getEmployee")
    ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees();

    @GetMapping("/getEmployee/{id}")
    ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Integer id);

    @PostMapping("/addEmployee")
    ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO);

    @PutMapping("/updateEmployee/{id}")
    ResponseEntity<EmployeeResponseDTO> updateEmployeeById(@PathVariable Integer id, @RequestBody EmployeeRequestDTO employeeRequestDTO);

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id);
    
    // 🔹 Correctly declare searchEmployees method
    @PostMapping("/search")
    ResponseEntity<List<EmployeeResponseDTO>> searchEmployees(@RequestBody EmployeeSearchDTO searchDTO);
}
