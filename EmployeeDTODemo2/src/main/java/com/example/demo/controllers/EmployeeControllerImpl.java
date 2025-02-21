package com.example.demo.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.responses.EmployeeApiResponse;
import com.example.demo.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeService service;

    @Override
    public ResponseEntity<EmployeeApiResponse> addEmployee(EmployeeDTO employeeDto) {
        log.info("<<START>> addEmployee <<START>>");
        ResponseEntity<EmployeeApiResponse> responseEntity =
                new ResponseEntity<>(service.addEmployee(employeeDto), HttpStatus.OK);
        log.info("<<END>> addEmployee <<END>>");
        return responseEntity;
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> getAllEmployees() {
        log.info("<<START>> getAllEmployees <<START>>");
        ResponseEntity<EmployeeApiResponse> responseEntity =
                new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
        log.info("<<END>> getAllEmployees <<END>>");
        return responseEntity;
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> updateEmployee(Integer id, EmployeeDTO employeeDto) {
        log.info("<<START>> updateEmployee <<START>>");
        ResponseEntity<EmployeeApiResponse> responseEntity =
                new ResponseEntity<>(service.updateEmployee(id, employeeDto), HttpStatus.OK);
        log.info("<<END>> updateEmployee <<END>>");
        return responseEntity;
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> getEmployeeById(Integer id) {
        log.info("<<START>> getEmployeeById <<START>>");
        ResponseEntity<EmployeeApiResponse> responseEntity =
                new ResponseEntity<>(service.getEmployeeById(id), HttpStatus.OK);
        log.info("<<END>> getEmployeeById <<END>>");
        return responseEntity;
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> deleteEmployeeById(Integer id) {
        log.info("<<START>> deleteEmployeeById <<START>>");
        ResponseEntity<EmployeeApiResponse> responseEntity =
                new ResponseEntity<>(service.deleteEmployeeById(id), HttpStatus.OK);
        log.info("<<END>> deleteEmployeeById <<END>>");
        return responseEntity;
    }

    @Override
    public ResponseEntity<EmployeeApiResponse> findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
        log.info("<<START>> findAllPagination <<START>>");
        ResponseEntity<EmployeeApiResponse> responseEntity =
                new ResponseEntity<>(service.findAllPagination(pageNum, pageSize), HttpStatus.OK);
        log.info("<<END>> findAllPagination <<END>>");
        return responseEntity;
    }
}
