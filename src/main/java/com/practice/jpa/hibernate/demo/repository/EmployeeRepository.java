package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.entity.Employee;
import com.practice.jpa.hibernate.demo.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    //insert an employee
    public void insert(Employee employee){
        em.persist(employee);
    }

    //retrieve all employees
    public List<Employee> retrieveAllEmployees(){
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    //for MappedSuperclass
    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        return em.createQuery("select e from PartTimeEmployee e",  PartTimeEmployee.class).getResultList();
    }

}
