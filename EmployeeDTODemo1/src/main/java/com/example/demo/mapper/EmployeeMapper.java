package com.example.demo.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import com.example.demo.dao.entities.Employee;
import com.example.demo.dto.EmployeeDTO;
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapEmployeeDtoToEmployeeEntity(EmployeeDTO employeeDTO);

    EmployeeDTO mapEmployeeEntityToEmployeeDto(Employee employee);

    List<EmployeeDTO> mapEmployeeEntityListToEmployeeDtoList(List<Employee> employeeList);

    default Page<EmployeeDTO> mapEmployeeEntityPageToEmployeeDtoPage(Page<Employee> employeePage) {
        return employeePage.map(this::mapEmployeeEntityToEmployeeDto);
    }
}
