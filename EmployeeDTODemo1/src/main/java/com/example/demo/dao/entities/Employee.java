package com.example.demo.dao.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "employees")
@Audited  
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean isActive;

    @Embedded
    private AuditEntity auditEntity;  
}
