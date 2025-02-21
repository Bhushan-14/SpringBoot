package com.example.demo.dao.repositries;

import com.example.demo.dao.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    
    List<Employee> findByIsDeletedFalse();

    Optional<Employee> findByEmployeeIdAndIsDeletedFalse(Integer employeeId);
}
