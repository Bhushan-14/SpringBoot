package com.example.demo.services;

import com.example.demo.dao.entities.Employee;
import com.example.demo.dao.repositries.EmployeeRepo;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.EmployeeSearchDTO;
import com.example.demo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findByIsDeletedFalse();
        return employees.stream()
                .map(EmployeeMapper.INSTANCE::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return EmployeeMapper.INSTANCE.toResponseDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeRequestDTO);
        employee.setIsDeleted(false); 
        employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.toResponseDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployeeById(Integer id, EmployeeRequestDTO employeeRequestDTO) {
        Employee existingEmployee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(employeeRequestDTO.getName());
        existingEmployee.setEmail(employeeRequestDTO.getEmail());

        employeeRepository.save(existingEmployee);
        return EmployeeMapper.INSTANCE.toResponseDTO(existingEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Integer id) {
        Employee existingEmployee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        existingEmployee.setIsDeleted(true); 
        employeeRepository.save(existingEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> searchEmployees(EmployeeSearchDTO searchDTO) {
        List<Employee> employees = employeeRepository.findAll()
                .stream()
                .filter(emp -> searchDTO.getEmployeeId() == null || emp.getEmployeeId().equals(searchDTO.getEmployeeId())) 
                .filter(emp -> searchDTO.getName() == null || emp.getName().toLowerCase().contains(searchDTO.getName().toLowerCase()))
                .filter(emp -> searchDTO.getEmail() == null || emp.getEmail().toLowerCase().contains(searchDTO.getEmail().toLowerCase()))
                .filter(emp -> searchDTO.getIsActive() == null || emp.getIsActive().equals(searchDTO.getIsActive()))
                .collect(Collectors.toList());

        return employees.stream().map(EmployeeMapper.INSTANCE::toResponseDTO).collect(Collectors.toList());
    }
}
