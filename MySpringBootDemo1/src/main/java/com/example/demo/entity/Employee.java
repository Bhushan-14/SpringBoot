package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @Column(nullable = false)
    private String empName;

    @Column(nullable = false)
    private String empDepartment;

    @Column(nullable = false)
    private double empSalary;

    @Column(nullable = false)
    private String empMail;

//    public Employee() {}
//
//    public Employee(int empId, String empName, String empDepartment, double empSalary, String empMail) {
//        this.empId = empId;
//        this.empName = empName;
//        this.empDepartment = empDepartment;
//        this.empSalary = empSalary;
//        this.empMail = empMail;
//    }
//
//    public int getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(int empId) {
//        this.empId = empId;
//    }
//
//    public String getEmpName() {
//        return empName;
//    }
//
//    public void setEmpName(String empName) {
//        this.empName = empName;
//    }
//
//    public String getEmpDepartment() {
//        return empDepartment;
//    }
//
//    public void setEmpDepartment(String empDepartment) {
//        this.empDepartment = empDepartment;
//    }
//
//    public double getEmpSalary() {
//        return empSalary;
//    }
//
//    public void setEmpSalary(double empSalary) {
//        this.empSalary = empSalary;
//    }
//
//    public String getEmpMail() {
//        return empMail;
//    }
//
//    public void setEmpMail(String empMail) {
//        this.empMail = empMail;
//    }
}
