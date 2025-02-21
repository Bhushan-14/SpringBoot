package com.example.demo.dao.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e JOIN e.departments d WHERE d.id = :deptId")
    List<Employee> findEmployeesByDepartmentId(@Param("deptId") Long deptId);

    @Query("SELECT DISTINCT e FROM Employee e JOIN e.departments d")
    List<Employee> findAllEmployeesWithDepartments();
}
