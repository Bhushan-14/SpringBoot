package com.example.demo.dao.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entities.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d JOIN d.employees e WHERE e.id = :empId")
    List<Department> findDepartmentsByEmployeeId(@Param("empId") Long empId);

    @Query("SELECT DISTINCT d FROM Department d JOIN d.employees e")
    List<Department> findAllDepartmentsWithEmployees();
}
