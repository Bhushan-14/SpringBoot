package com.example.demo.services;

import com.example.demo.dao.entities.Department;
import com.example.demo.dao.entities.Employee;
import com.example.demo.dao.repositries.DepartmentRepository;
import com.example.demo.dao.repositries.EmployeeRepository;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.response.DepartmentApiResponse;
import com.example.demo.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentApiResponse getDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            DepartmentDTO departmentDTO = departmentMapper.mapEntityToDto(department);
            
            if (department.getEmployees() != null) {
                departmentDTO.setEmployeeIds(
                    department.getEmployees().stream().map(Employee::getId).collect(Collectors.toSet())
                );
            }
            return new DepartmentApiResponse(departmentDTO, null, HttpStatus.OK, "Department found", false);
        }
        return new DepartmentApiResponse(null, null, HttpStatus.NOT_FOUND, "Department not found", true);
    }


    @Override
    public DepartmentApiResponse getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        if (departmentList.isEmpty()) {
            return new DepartmentApiResponse(null, null, HttpStatus.NOT_FOUND, "No departments found", true);
        }
        List<DepartmentDTO> dtoList = departmentList.stream()
                .map(departmentMapper::mapEntityToDto)
                .toList();
        return new DepartmentApiResponse(null, dtoList, HttpStatus.OK, "Departments fetched successfully", false);
    }

    @Override
    @Transactional
    public DepartmentApiResponse createDepartment(DepartmentDTO dto) {
        Department department = departmentMapper.mapDtoToEntity(dto);
        department.setActive(true);

        if (dto.getEmployeeIds() != null && !dto.getEmployeeIds().isEmpty()) {
            List<Employee> employees = employeeRepository.findAllById(dto.getEmployeeIds());
            department.setEmployees(new HashSet<>(employees));
        }

        Department savedDepartment = departmentRepository.save(department);
        DepartmentDTO savedDepartmentDto = departmentMapper.mapEntityToDto(savedDepartment);
        return new DepartmentApiResponse(savedDepartmentDto, null, HttpStatus.CREATED, "Department created successfully", false);
    }

    @Override
    @Transactional
    public DepartmentApiResponse updateDepartment(Long id, DepartmentDTO dto) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department departmentToUpdate = departmentOptional.get();
            departmentToUpdate.setName(dto.getName());

            if (dto.getEmployeeIds() != null && !dto.getEmployeeIds().isEmpty()) {
                List<Employee> employees = employeeRepository.findAllById(dto.getEmployeeIds());
                departmentToUpdate.setEmployees(new HashSet<>(employees));
            }

            departmentRepository.save(departmentToUpdate);
            DepartmentDTO updatedDepartmentDto = departmentMapper.mapEntityToDto(departmentToUpdate);
            return new DepartmentApiResponse(updatedDepartmentDto, null, HttpStatus.OK, "Department updated successfully", false);
        }
        return new DepartmentApiResponse(null, null, HttpStatus.NOT_FOUND, "Department not found", true);
    }
    @Override
    @Transactional
    public DepartmentApiResponse deleteDepartment(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setActive(false);
            department.setDeleted(true); // Mark as deleted

            departmentRepository.save(department);
            log.info("Deleted Department: {}", department);

            return new DepartmentApiResponse(departmentMapper.mapEntityToDto(department),
                    null, HttpStatus.OK, "Department deleted successfully", false);
        }
        return new DepartmentApiResponse(null, null, HttpStatus.NOT_FOUND, "Department not found", true);
    }
}
