package com.example.demo.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, RevisionRepository<Employee, Integer, Integer> {

    @Query("FROM Employee e WHERE e.isActive = true ORDER BY e.id DESC")
    Page<Employee> findAllPagination(Pageable page);

    @Query("FROM Employee e WHERE e.isActive = true")
    List<Employee> findAllEmployeeDetails();

    @Query("FROM Employee e WHERE e.id = ?1 AND e.isActive = true")
    Optional<Employee> findByEmployeeId(Integer id);  

    @Query("FROM Employee e WHERE e.name = ?1 AND e.isActive = true")
    Employee findByEmployeeName(String name);
}
