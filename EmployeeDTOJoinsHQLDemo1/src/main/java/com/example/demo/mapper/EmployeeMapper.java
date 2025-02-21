package com.example.demo.mapper;

import com.example.demo.dao.entities.Employee;
import com.example.demo.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "departments", ignore = true) 
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "departments", ignore = true) 
    Employee toEntity(EmployeeDTO employeeDTO);
}
