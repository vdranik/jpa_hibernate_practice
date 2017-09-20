package com.practice.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne //EAGER
    private Course course;

    //private String rating;

    @Enumerated(EnumType.STRING)
    private ReviewRating rating;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    protected Review(){}

//    public Review(String rating, String description) {
//        this.rating = rating;
//        this.description = description;
//    }

    public Review(ReviewRating rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getRating() {
//        return rating;
//    }
//
//    public void setRating(String rating) {
//        this.rating = rating;
//    }


    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Review{");
        sb.append("id=").append(id);
        sb.append(", rating='").append(rating).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", createdDate=").append(createdDate);
        sb.append(", lastUpdatedDate=").append(lastUpdatedDate);
        sb.append('}');
        return sb.toString();
    }
}
