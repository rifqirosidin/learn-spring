package com.rifqi.springboot.cruddemo.dao;

import com.rifqi.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        Query query = entityManager.createQuery("from Employee", Employee.class);

        //execute uery and get result list
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee employee = entityManager.find(Employee.class, theId);

        return employee;
    }

    @Override
    public void save(Employee theEmployee) {
        // save or update the employee
        Employee employee = entityManager.merge(theEmployee);

        // update with id form db so we can get generated id for save/insert
        employee.setId(employee.getId());
    }

    @Override
    public void deleteById(int theId) {
        Query query = entityManager.createQuery("delete from Employee  where id=:employeeId");
        query.setParameter("employeeId", theId);

        query.executeUpdate();

    }
}
