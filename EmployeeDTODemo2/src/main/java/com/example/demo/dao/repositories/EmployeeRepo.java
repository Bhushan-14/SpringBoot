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
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

    @Query("from Employee e where e.isDeleted=false AND e.isActive=true ORDER BY e.employeeId DESC")
    Page<Employee> findAllpagination(Pageable page);

    @Query("from Employee e where e.isDeleted=false AND e.isActive=true ORDER BY e.employeeId ASC") 
    List<Employee> findAllEmployeeDetails();

    @Query("select e from Employee e where e.employeeId=?1 AND e.isDeleted=false AND e.isActive=true")
    Optional<Employee> findByEmployeeId(Integer id);
    
    @Query("From Employee e where e.email=?1 and e.isDeleted=false and e.isActive=true")
    Employee findByEmail(String email);
}
