package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.response.EmployeeApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employees")
public interface EmployeeController {

    @GetMapping("/{id}")
    ResponseEntity<EmployeeApiResponse> getEmployeeById(@PathVariable("id") Integer employeeId);

    @PostMapping
    ResponseEntity<EmployeeApiResponse> createEmployee(@RequestBody EmployeeDTO employeeDto);

    @PutMapping("/{id}")
    ResponseEntity<EmployeeApiResponse> updateEmployee(@PathVariable("id") Integer employeeId,
                                                       @RequestBody EmployeeDTO employeeDto);

    @DeleteMapping("/{id}")
    ResponseEntity<EmployeeApiResponse> deleteEmployee(@PathVariable("id") Integer employeeId);

    @GetMapping
    ResponseEntity<EmployeeApiResponse> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size);

    @GetMapping("/paginated")
    ResponseEntity<EmployeeApiResponse> getEmployeesWithPagination(Pageable pageable);
}
