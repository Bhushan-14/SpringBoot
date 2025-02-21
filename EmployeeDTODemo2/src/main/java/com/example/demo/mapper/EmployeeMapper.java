package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import com.example.demo.dao.entities.Employee;
import com.example.demo.dto.EmployeeDTO;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "auditEntity", ignore = true)
    Employee mapDtoToEntity(EmployeeDTO employeeDto);

    EmployeeDTO mapEntityToDto(Employee employee);

    List<EmployeeDTO> mapEmployeeEntityListToEmployeeDtoList(List<Employee> employeeList);

    default Page<EmployeeDTO> mapEmployeeEntityPageToEmployeeDtoPage(Page<Employee> employeePage) {
        return employeePage.map(this::mapEntityToDto);
    }
}
