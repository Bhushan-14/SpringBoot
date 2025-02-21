package com.example.demo.controller;
import com.example.demo.entity.Employee;
import com.example.demo.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeServices.getAllEmployees();
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId) {
        Optional<Employee> employee = employeeServices.getEmployeeById(empId);
        System.out.println("Retrieved employee data using employee ID: "+empId);
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeServices.insertEmployee(employee);
    }

    @PutMapping("/employee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int empId, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeServices.updateEmployee(empId, employee);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int empId) {
    	System.out.println("Delete employee " + empId );
        employeeServices.deleteEmployee(empId);
        return ResponseEntity.noContent().build();
    }
}
