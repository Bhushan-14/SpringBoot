package com.example.demo.dto.response;

import com.example.demo.dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentApiResponse {
    private DepartmentDTO department;
    private List<DepartmentDTO> departmentList;
    private HttpStatus status;
    private String message;
    private boolean error;
}
