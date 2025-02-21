package com.example.demo.mapper;

import com.example.demo.dao.entities.Department;
import com.example.demo.dto.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(target = "employees", ignore = true) 
    DepartmentDTO toDTO(Department department);

    @Mapping(target = "employees", ignore = true) 
    Department toEntity(DepartmentDTO departmentDTO);
}
