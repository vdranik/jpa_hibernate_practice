package com.practice.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "number", nullable = false)
    private String number;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "passport")
    private Student student;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    protected Passport(){}

    public Passport(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Passport{");
        sb.append("id=").append(id);
        sb.append(", number='").append(number).append('\'');
        sb.append(", student=").append(student);
        sb.append('}');
        return sb.toString();
    }
}
