package com.abs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test_")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int empId;

    private  String empName;

    private String empAddress;

    private  String empEmailId;

    private  String empPassword;

    private  long empContact;

    private double empSalary;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;
}
