package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.responses.EmployeeApiResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
public interface EmployeeController {

    @GetMapping("/getByEmployeeId/{id}")
    ResponseEntity<EmployeeApiResponse> getEmployeeById(@PathVariable("id") Integer id);

    @GetMapping("/getAllEmployees")
    ResponseEntity<EmployeeApiResponse> getAllEmployees();

    @PostMapping("/addEmployee")
    ResponseEntity<EmployeeApiResponse> addEmployee(@RequestBody EmployeeDTO employeeDto);

    @DeleteMapping("/deleteByEmployeeId/{id}")
    ResponseEntity<EmployeeApiResponse> deleteEmployeeById(@PathVariable("id") Integer id);

    @PutMapping("/updateEmployee/{id}")
    ResponseEntity<EmployeeApiResponse> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDTO employeeDto);

    @GetMapping("/paginationEmployee")
    ResponseEntity<EmployeeApiResponse> findAllPagination(
            @RequestParam(name = "pageNum", defaultValue = "0") Optional<Integer> pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Optional<Integer> pageSize);
}
