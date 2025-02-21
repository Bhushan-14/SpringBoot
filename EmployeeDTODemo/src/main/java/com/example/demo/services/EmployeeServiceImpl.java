package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findByDeletedFalse();
        return employees.stream()
                        .map(employeeMapper::toDTO)
                        .toList();
    }

    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long empId) {
        Optional<Employee> employee = employeeRepo.findById(empId);
        return employee.filter(emp -> !emp.getDeleted())
                       .map(employeeMapper::toDTO);
    }

    @Override
    public EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee.setDeleted(false);
        employee = employeeRepo.save(employee);
        return employeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepo.findById(empId)
                                        .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee = employeeMapper.toEntity(employeeDTO);
        employee.setId(empId);  
        employee = employeeRepo.save(employee);
        return employeeMapper.toDTO(employee);
    }

    @Override
    public void softDeleteEmployee(Long empId) {
        Employee employee = employeeRepo.findById(empId).orElseThrow(() -> 
        	new RuntimeException("Employee not found"));
        employee.setDeleted(true);  
        employeeRepo.save(employee);
    }
}
