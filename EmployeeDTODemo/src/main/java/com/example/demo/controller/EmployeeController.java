package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public Optional<EmployeeDTO> getEmployeeById(@PathVariable Long empId) {
        return employeeService.getEmployeeById(empId);
    }

    @PostMapping
    public EmployeeDTO insertEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.insertEmployee(employeeDTO);
    }

    @PutMapping("/{empId}")
    public EmployeeDTO updateEmployee(@PathVariable Long empId, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(empId, employeeDTO);
    }

    @DeleteMapping("/{empId}")
    public void deleteEmployee(@PathVariable Long empId) {
        employeeService.softDeleteEmployee(empId);
    }
}
