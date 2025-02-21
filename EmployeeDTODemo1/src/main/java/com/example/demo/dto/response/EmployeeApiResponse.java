package com.example.demo.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.example.demo.dto.EmployeeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeApiResponse {
    private EmployeeDTO employeeDTO;
    private List<EmployeeDTO> employeeDTOs;
    private Page<EmployeeDTO> employeeDTOPage;
    private HttpStatus status;
    private String message;
    private boolean error;
}
