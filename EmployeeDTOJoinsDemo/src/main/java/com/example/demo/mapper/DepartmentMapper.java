package com.example.demo.mapper;

import com.example.demo.dao.entities.Department;
import com.example.demo.dto.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDTO mapEntityToDto(Department department);

    @Mapping(target = "employees", ignore = true)
    Department mapDtoToEntity(DepartmentDTO departmentDTO);

    List<DepartmentDTO> mapEntityListToDtoList(List<Department> departments);
}
