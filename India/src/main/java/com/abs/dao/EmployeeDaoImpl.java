package com.abs.dao;

import com.abs.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import javax.persistence.Table;
import java.util.List;

@Component

public class EmployeeDaoImpl implements EmployeeDao{

    private  static SessionFactory factory= new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void save(Employee employee) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(employee);

        transaction.commit();

    }

    @Override
    public boolean signIn(String empEmaildId, String empPassword) {
        boolean flag= false;

        for(Employee emp : findAll()){
            if(emp.getEmpEmailId().equals(empEmaildId) && emp.getEmpPassword().equals(empPassword)){
                flag= true;
                break;
            }
        }

        return flag;
    }

    @Override
    public Employee findByID(int empId) {
        Session session = factory.openSession();
        return (Employee) session.load(Employee.class ,empId);
    }

    @Override
    public List<Employee> findAll() {
        Session session = factory.openSession();
        return session.createQuery("from Employee").list();
    }

    @Override
    public void upDateData(int empId, Employee employee)
    {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee1 = findByID(empId);

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpContact(employee.getEmpContact());

         session.update(employee1);
         transaction.commit();

    }

    @Override
    public void deleteById(int empId) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.delete(session.get(Employee.class,empId));

        transaction.commit();


    }

    @Override
    public void deleteAll() {
        Session session = factory.openSession();

        for(Employee emp : findAll())
        {
            Transaction transaction = session.beginTransaction();
            session.delete(emp);
            transaction.commit();
        }

    }
}
