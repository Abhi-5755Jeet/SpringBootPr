package com.abs.service;

import com.abs.dao.EmployeeDao;
import com.abs.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private EmployeeDao employeeDaoimpl;


    @Override
    public void save(Employee employee) {
        employeeDaoimpl.save(employee);
    }

    @Override
    public boolean signIn(String empEmaildId, String empPassword) {
        return employeeDaoimpl.signIn(empEmaildId, empPassword);
    }

    @Override
    public Employee findByID(int empId) {
        return employeeDaoimpl.findByID(empId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDaoimpl.findAll();
    }

    @Override
    public void upDateData(int empId, Employee employee) {
        employeeDaoimpl.upDateData(empId ,employee);

    }

    @Override
    public void deleteById(int empId) {

        employeeDaoimpl.deleteById(empId);


    }

    @Override
    public void deleteAll() {

        employeeDaoimpl.deleteAll();

    }
}
