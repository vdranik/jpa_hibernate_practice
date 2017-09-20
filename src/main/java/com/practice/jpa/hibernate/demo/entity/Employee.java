package com.practice.jpa.hibernate.demo.entity;

import javax.persistence.*;

//@Entity //if only entity = employee sigle table
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "EmployeeType") //rename DTYPE column
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 2 tables
//@Inheritance(strategy = InheritanceType.JOINED) // 3 tables, simple query - SELECT * FROM PART_TIME_EMPLOYEE, EMPLOYEE WHERE PART_TIME_EMPLOYEE.ID = EMPLOYEE.ID
@MappedSuperclass // very similar to table_per_class strategy
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Employee(){}

    public Employee(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
