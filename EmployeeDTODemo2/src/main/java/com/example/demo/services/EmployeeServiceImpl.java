package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entities.Employee;
import com.example.demo.dao.entities.AuditEntity;
import com.example.demo.dao.repositories.EmployeeRepo;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.responses.EmployeeApiResponse;
import com.example.demo.mapper.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo repository;

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public EmployeeApiResponse getEmployeeById(Integer id) {
        try {
            log.info("Finding Employee with ID: {}", id);
            Optional<Employee> employeeFetchedFromDb = repository.findByEmployeeId(id);

            if (employeeFetchedFromDb.isPresent()) {
                Employee employee = employeeFetchedFromDb.get();
                EmployeeDTO employeeDto = mapper.mapEntityToDto(employee);
                return new EmployeeApiResponse(employeeDto, null, null, HttpStatus.OK, "Employee Details Found", false);
            } else {
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Employee ID not found", true);
            }
        } catch (Exception e) {
            log.error("An error occurred while finding Employee with ID: {}", id, e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", true);
        }
    }

    @Override
    public EmployeeApiResponse getAllEmployees() {
        try {
            List<Employee> employeeList = repository.findAllEmployeeDetails();
            if (!employeeList.isEmpty()) {
                List<EmployeeDTO> employeeDtos = employeeList.stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
                return new EmployeeApiResponse(null, employeeDtos, null, HttpStatus.FOUND, "Employee List Found", false);
            } else {
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Employee List Not Found", true);
            }
        } catch (Exception e) {
            log.error("Exception occurs in getAllEmployees method", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
        }
    }

    @Override
    public EmployeeApiResponse addEmployee(EmployeeDTO employeeDto) {
        try {
            Employee employeeToSave = mapper.mapDtoToEntity(employeeDto);
            if (employeeToSave.getIsActive() == null) {
                employeeToSave.setIsActive(true);
            }
            if (employeeToSave.getIsDeleted() == null) {
                employeeToSave.setIsDeleted(false);
            }

            employeeToSave.setAuditEntity(new AuditEntity("Admin", LocalDateTime.now(), null, null));

            Employee savedEmployee = repository.save(employeeToSave);
            EmployeeDTO employeeDtoToSend = mapper.mapEntityToDto(savedEmployee);
            return new EmployeeApiResponse(employeeDtoToSend, null, null, HttpStatus.CREATED, "Employee saved successfully", false);
        } catch (Exception e) {
            log.error("An error occurred while saving Employee", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", true);
        }
    }


    @Override
    public EmployeeApiResponse deleteEmployeeById(Integer id) {
        try {
            Optional<Employee> employeeOptional = repository.findByEmployeeId(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setIsDeleted(true);
                employee.setIsActive(false);
                repository.save(employee);  
                return new EmployeeApiResponse(mapper.mapEntityToDto(employee),null, null,HttpStatus.OK,"Employee soft deleted successfully",false);
            } else {
                return new EmployeeApiResponse(null, null, null,HttpStatus.NOT_FOUND,"Employee not found",true);
            }
        } catch (Exception e) {
            log.error("An error occurred while soft deleting Employee", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR,"Server Error",true);
        }
    }

    @Override
    public EmployeeApiResponse updateEmployee(Integer id, EmployeeDTO employeeDto) {
        try {
            Optional<Employee> employeeFetchedFromDb = repository.findByEmployeeId(id);

            if (employeeFetchedFromDb.isPresent()) {
                Employee existingEmployee = employeeFetchedFromDb.get();

                if (employeeDto.getName() != null) {
                    existingEmployee.setName(employeeDto.getName());
                }
                if (employeeDto.getAge() != null) {
                    existingEmployee.setAge(employeeDto.getAge());
                }
                if (employeeDto.getEmail() != null) {
                    existingEmployee.setEmail(employeeDto.getEmail());
                }
                if (employeeDto.getIsActive() != null) {
                    existingEmployee.setIsActive(employeeDto.getIsActive());
                }
                
                AuditEntity auditEntity = existingEmployee.getAuditEntity();
                if (auditEntity == null) {
                    auditEntity = new AuditEntity();
                    auditEntity.setCreatedBy("Unknown");
                    auditEntity.setCreatedAt(LocalDateTime.now());
                }
                auditEntity.setUpdatedBy("Admin");
                auditEntity.setUpdatedAt(LocalDateTime.now());

                existingEmployee.setAuditEntity(auditEntity);

                Employee updatedEmployee = repository.save(existingEmployee);
                EmployeeDTO updatedEmployeeDto = mapper.mapEntityToDto(updatedEmployee);

                return new EmployeeApiResponse(updatedEmployeeDto, null, null, HttpStatus.OK, "Employee updated successfully", false);
            } else {
                return new EmployeeApiResponse(employeeDto, null, null, HttpStatus.NOT_FOUND, "Employee not found", true);
            }
        } catch (Exception e) {
            log.error("An error occurred while updating Employee", e);
            return new EmployeeApiResponse(employeeDto, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", true);
        }
    }

    @Override
    public EmployeeApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        try {
            int page = pageNum.orElse(0);
            int size = pageSize.orElse(10);
            Pageable pageable = PageRequest.of(page, size);
            Page<Employee> employeePage = repository.findAllpagination(pageable);

            if (employeePage.isEmpty()) {
                return new EmployeeApiResponse(null, null, null, HttpStatus.NOT_FOUND, "No Employees found", false);
            } else {
                List<EmployeeDTO> dtos = employeePage.getContent().stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
                return new EmployeeApiResponse(null, dtos, null, HttpStatus.OK, "Employees retrieved successfully", true);
            }
        } catch (Exception e) {
            log.error("An error occurred while retrieving Employees with pagination", e);
            return new EmployeeApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred", true);
        }
    }
}
