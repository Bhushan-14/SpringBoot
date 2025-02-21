package com.example.demo.controllers;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.response.DepartmentApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public interface DepartmentController {

    @GetMapping("/{id}")
    ResponseEntity<DepartmentApiResponse> getDepartmentById(@PathVariable Long id);

    @GetMapping("/all")
    ResponseEntity<DepartmentApiResponse> getAllDepartments();

    @PostMapping("/addDepartment")
    ResponseEntity<DepartmentApiResponse> createDepartment(@RequestBody DepartmentDTO departmentDTO);

    @PutMapping("/updateDepartment/{id}")
    ResponseEntity<DepartmentApiResponse> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO);

    @DeleteMapping("/deleteDepartment/{id}")
    ResponseEntity<DepartmentApiResponse> deleteDepartment(@PathVariable Long id);
}
