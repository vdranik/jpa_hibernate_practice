package com.practice.jpa.hibernate.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Course")
//@NamedQuery(name = "query_get_all_courses", query = "Select c FROM Course c")
//@NamedQuery(name = "query_get_all_courses_100_Step_courses", query = "Select c From Course c where name like '%100 steps'") // we can't use two same annotations
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses", query = "Select c FROM Course c"),
        @NamedQuery(name = "query_get_all_courses_100_Step_courses", query = "Select c From Course c where name like '%100 steps'"),
        @NamedQuery(name = "query_get_all_courses_join_fetch", query = "Select c From Course c JOIN FETCH c.students s")})
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class Course {

    private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course") //default LAZY
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")//default LAZY
    @JsonIgnore // fix recursive repeating json when we call http://localhost:8080/courses
    private List<Student> students = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    //for soft deleting
    private boolean isDeleted;

    @PreRemove //also we have @PostLoad, @PostPersist, @PostRemove, @PostUpdate, @PrePersist, @PreRemove, @PreUpdate
    private void preRemove(){
        LOGGER.info("\n\nSetting is_deleted to true\n\n");
        this.isDeleted = true;
    }

    protected Course(){}

    public Course(String name) {
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

//    @Override
//    public String toString() {
//        final StringBuffer sb = new StringBuffer("\nCourse{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append('}');
//        return sb.toString();
//    }


    //YOU NEED BE VERY CAREFUL ABOUT USE YOUR TOSRTING METHOD
    @Override
    public String toString() {
        return String.format("Course[%s] Review[%s]", name, reviews); //in hibernate we will have 2 quries - courses and reviews
    }
}
