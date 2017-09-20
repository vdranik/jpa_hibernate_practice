package com.practice.jpa.hibernate.demo.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee{

    private BigDecimal hourlyWage;

    protected PartTimeEmployee(){
    }

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PartTimeEmployee{");
        sb.append(super.toString());
        sb.append("hourlyWage=").append(hourlyWage);
        sb.append('}');
        return sb.toString();
    }
}
