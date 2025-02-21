package com.example.demo.services;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {

    private final EmployeeRepo empRepo;

    @Autowired
    public EmployeeServices(EmployeeRepo empRepo) {
        this.empRepo = empRepo;
    }

    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(int empId) {
        return empRepo.findById(empId);
    }

    public Employee insertEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    public Employee updateEmployee(int empId, Employee employee) {
        if (empRepo.existsById(empId)) {
            employee.setEmpId(empId);
            return empRepo.save(employee);
        }
        return null;
    }

    public void deleteEmployee(int empId) {
        empRepo.deleteById(empId);
    }
}
