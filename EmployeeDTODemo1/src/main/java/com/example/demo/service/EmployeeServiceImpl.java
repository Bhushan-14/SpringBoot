package com.example.demo.service;

import com.example.demo.dao.entities.Employee;
import com.example.demo.dao.repositories.EmployeeRepository;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.response.EmployeeApiResponse;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeApiResponse getEmployeeById(Integer id) {
        try {
            log.info("Fetching Employee with ID: {}", id);
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(id);
            if (employeeOptional.isPresent()) {
                EmployeeDTO employeeDto = employeeMapper.mapEmployeeEntityToEmployeeDto(employeeOptional.get());
                log.info("Employee found with ID: {}", id);
                return new EmployeeApiResponse(employeeDto, null, null, HttpStatus.OK, "Employee found", false);
            } else {
                log.warn("Employee not found with ID: {}", id);
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Employee ID not found", true);
            }
        } catch (Exception e) {
            log.error("Error while fetching Employee with ID: {}", id, e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
        }
    }

    @Override
    public EmployeeApiResponse getAllEmployees(Pageable pageable) {
        try {
            log.info("Fetching all active employees with pagination");
            Page<Employee> employeePage = employeeRepository.findAllPagination(pageable);
            if (employeePage.isEmpty()) {
                log.warn("No active employees found");
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "No employees found", true);
            }
            List<EmployeeDTO> dtoList = employeeMapper.mapEmployeeEntityListToEmployeeDtoList(employeePage.getContent());
            Page<EmployeeDTO> dtoPage = employeePage.map(employeeMapper::mapEmployeeEntityToEmployeeDto);
            return new EmployeeApiResponse(null, dtoList, dtoPage, HttpStatus.OK, "Employees fetched successfully", false);
        } catch (Exception e) {
            log.error("Error while fetching employees", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
        }
    }

    @Override
    @Transactional
    public EmployeeApiResponse createEmployee(EmployeeDTO dto) {
        try {
            log.info("Creating new Employee with name: {}", dto.getName());
            // Check if an employee with the same name already exists
            Employee existingEmployee = employeeRepository.findByEmployeeName(dto.getName());
            if (existingEmployee != null) {
                log.warn("Employee with name {} already exists", dto.getName());
                return new EmployeeApiResponse(null, null, null, HttpStatus.CONFLICT, "Employee with this name already exists", true);
            }
            Employee employee = employeeMapper.mapEmployeeDtoToEmployeeEntity(dto);
            employee.setIsActive(true);  // Use setActive because the field is named 'active'
            Employee savedEmployee = employeeRepository.save(employee);
            EmployeeDTO savedEmployeeDto = employeeMapper.mapEmployeeEntityToEmployeeDto(savedEmployee);
            log.info("Employee created successfully with ID: {}", savedEmployee.getId());
            return new EmployeeApiResponse(savedEmployeeDto, null, null, HttpStatus.CREATED, "Employee saved successfully", false);
        } catch (Exception e) {
            log.error("Error while creating Employee", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
        }
    }

    @Override
    @Transactional
    public EmployeeApiResponse updateEmployee(Integer id, EmployeeDTO dto) {
        try {
            log.info("Updating Employee with ID: {}", id);
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(id);
            if (employeeOptional.isPresent()) {
                Employee employeeToUpdate = employeeOptional.get();
                // Update fields
                employeeToUpdate.setName(dto.getName());
                employeeToUpdate.setAge(dto.getAge());
                employeeToUpdate.setEmail(dto.getEmail());
                // Save updated employee
                Employee updatedEmployee = employeeRepository.save(employeeToUpdate);
                EmployeeDTO updatedEmployeeDto = employeeMapper.mapEmployeeEntityToEmployeeDto(updatedEmployee);
                log.info("Employee updated successfully with ID: {}", updatedEmployee.getId());
                return new EmployeeApiResponse(updatedEmployeeDto, null, null, HttpStatus.OK, "Employee updated successfully", false);
            } else {
                log.warn("Employee not found with ID: {}", id);
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Employee not found", true);
            }
        } catch (Exception e) {
            log.error("Error while updating Employee with ID: {}", id, e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
        }
    }

    @Override
    @Transactional
    public EmployeeApiResponse deleteEmployee(Integer id) {
        try {
            log.info("Deleting (soft) Employee with ID: {}", id);
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setIsActive(false);  // Soft delete by setting active to false
                employeeRepository.save(employee);
                EmployeeDTO deletedEmployeeDto = employeeMapper.mapEmployeeEntityToEmployeeDto(employee);
                log.info("Employee soft-deleted with ID: {}", id);
                return new EmployeeApiResponse(deletedEmployeeDto, null, null, HttpStatus.OK, "Employee deleted successfully", false);
            } else {
                log.warn("Employee not found with ID: {}", id);
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Employee not found", true);
            }
        } catch (Exception e) {
            log.error("Error while deleting Employee", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
        }
    }

    @Override
    public EmployeeApiResponse findEmployeesWithPagination(Pageable pageable) {
        try {
            log.info("Fetching Employees with pagination");
            Page<Employee> employeePage = employeeRepository.findAllPagination(pageable);
            if (employeePage.isEmpty()) {
                log.warn("No employees found for the given page");
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "No employees found", true);
            }
            List<EmployeeDTO> dtoList = employeeMapper.mapEmployeeEntityListToEmployeeDtoList(employeePage.getContent());
            Page<EmployeeDTO> dtoPage = employeePage.map(employeeMapper::mapEmployeeEntityToEmployeeDto);
            return new EmployeeApiResponse(null, dtoList, dtoPage, HttpStatus.OK, "Employees retrieved successfully", false);
        } catch (Exception e) {
            log.error("Error while fetching employees with pagination", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server error", true);
        }
    }
}
