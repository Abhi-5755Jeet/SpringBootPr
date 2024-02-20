package com.abs.dao;

import com.abs.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee employee);

   boolean signIn(String empEmaildId , String empPassword);

    Employee findByID(int empId);

    List<Employee>  findAll();

    void upDateData(int empId , Employee employee);

    void deleteById(int empId);

    void deleteAll();

}
