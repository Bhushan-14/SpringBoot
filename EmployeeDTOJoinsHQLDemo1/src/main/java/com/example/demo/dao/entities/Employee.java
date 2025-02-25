package com.example.demo.dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    private String country;
    private String state;
    private String district;
    private String city;
    private String street;
    private String pincode;

    @ManyToMany
    @JoinTable(
        name = "employee_department",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments; 
}
