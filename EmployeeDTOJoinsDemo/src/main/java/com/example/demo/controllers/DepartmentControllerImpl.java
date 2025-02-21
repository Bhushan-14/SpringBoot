package com.example.demo.controllers;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.response.DepartmentApiResponse;
import com.example.demo.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;

    @Override
    public ResponseEntity<DepartmentApiResponse> getDepartmentById(@PathVariable Long id) {
        log.info("Fetching Department by ID: {}", id);
        DepartmentApiResponse response = departmentService.getDepartmentById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<DepartmentApiResponse> getAllDepartments() {
        log.info("Fetching all Departments");
        DepartmentApiResponse response = departmentService.getAllDepartments();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<DepartmentApiResponse> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        log.info("Creating new Department: {}", departmentDTO.getName());
        DepartmentApiResponse response = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<DepartmentApiResponse> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        log.info("Updating Department with ID: {}", id);
        DepartmentApiResponse response = departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public ResponseEntity<DepartmentApiResponse> deleteDepartment(@PathVariable Long id) {
        log.info("Deleting Department with ID: {}", id);
        DepartmentApiResponse response = departmentService.deleteDepartment(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
