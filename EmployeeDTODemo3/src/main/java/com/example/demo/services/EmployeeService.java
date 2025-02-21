package com.example.demo.services;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.EmployeeSearchDTO;
import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO getEmployeeById(Integer id);
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO updateEmployeeById(Integer id, EmployeeRequestDTO employeeRequestDTO);
    void deleteEmployeeById(Integer id);

    // 🔹 Add search method
    List<EmployeeResponseDTO> searchEmployees(EmployeeSearchDTO searchDTO);
}
