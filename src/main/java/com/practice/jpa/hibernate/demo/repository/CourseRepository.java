package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.entity.Course;
import com.practice.jpa.hibernate.demo.entity.Review;
import com.practice.jpa.hibernate.demo.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    //YOU NEED BE VERY CAREFUL ABOUT USE YOUR TOSRTING METHOD (Course class, CourseRepositoryTest findById())
    public Course findById(Long id){
        Course course = em.find(Course.class, id);
        logger.info("Course -> {}", course); //watching only course, but in hibernate we will have 2 quries - courses and reviews
        return course;
    }

    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course){
        if(course.getId() == null){
            //insert
            em.persist(course);
        } else {
            //update
            em.merge(course);
        }

        return course;
    }

    public void playWithEntityManager(){
        logger.info("playWithEntityManager START!");

        Course course1 = new Course("Web Services in 100 steps");
        em.persist(course1);
        Course course2 = new Course("AngularJS in 100 steps");
        em.persist(course2);
        em.flush();

//        em.detach(course1);
//        em.detach(course2);
//        em.clear(); //detached everything

        course1.setName("Web Services in 100 steps - UPDATED");
        course2.setName("AngularJS in 100 steps - UPDATED");
        em.refresh(course1);
        em.merge(course1);
        em.merge(course2);
        //em.flush();
    }

//    public void addReviewsHardcodedForCourse() {
//        //get the course 10003
//        Course course = findById(10003L);
//        logger.info("course.getReviews() -> {}", course.getReviews());
//
//        //add 2 reviews to it
//        Review review1 = new Review("5", "Great Hands-on Stuff");
//        Review review2 = new Review("5", "Hatsoff");
//
//        course.addReview(review1);
//        review1.setCourse(course);
//
//        course.addReview(review2);
//        review2.setCourse(course);
//
//        // save it to the db
//        em.persist(review1);
//        em.persist(review2);
//    }

    public void addReviewsForCourse(Long courseId, List<Review> reviewList) throws Exception {
        //get the course 10003
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
//        for(Review review: reviewList){
//            course.addReview(review);
//            review.setCourse(course);
//            em.persist(review);
//        }

        reviewList.forEach(review -> addReviewToCourse(review, course)); //java8


    }

    private void addReviewToCourse(Review review, Course course){
        course.addReview(review);
        review.setCourse(course);
        em.persist(review);
    }

    public void addReviewsHardcodedForCourse() {
        //get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff");
        Review review2 = new Review(ReviewRating.FIVE, "Hatsoff");

        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        // save it to the db
        em.persist(review1);
        em.persist(review2);
    }
}
