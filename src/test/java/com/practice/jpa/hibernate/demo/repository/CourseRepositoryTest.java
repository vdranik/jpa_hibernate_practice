package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.DemoApplication;
import com.practice.jpa.hibernate.demo.entity.Course;
import com.practice.jpa.hibernate.demo.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

    //JPA @Transactional works only with one DB
    //Spring @Transactional works with many DBs and JMS and supports isolation level

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    CourseRepository courseRepository;

	@Autowired
    EntityManager em;

    @Test
    public void contextLoads() {
        logger.info("Testing is running!");
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10001L);
        assertNull(courseRepository.findById(10001L));
    }

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @Transactional
    public void findById_firstlevelCacheDemo() {
        Course course = courseRepository.findById(10001L);
        logger.info("Fisrt course retrieve -> \n\n{}\n\n", course);

        //retrieve the same data again
        Course course2 = courseRepository.findById(10001L);
        //we don't need a second query to db in this transaction scope!!!
        // but if we commited //@Transactional we will have 2 queries to db
        logger.info("Fisrt course retrieve AGAIN -> \n\n{}\n\n", course);

        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        //get course
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());

        //update details
        course.setName("JPA in 50 steps - UPDATED");
        courseRepository.save(course);

        //check the value
        Course updatedCourse = courseRepository.findById(10001L);
        assertEquals("JPA in 50 steps - UPDATED", updatedCourse.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager_basic() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        logger.info("\n\n{}\n\n", review.getCourse());
    }

    @Test
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void retrieveCourseForReviewAndIsolationLevel() {
        Review review = em.find(Review.class, 50001L);
        logger.info("\n\n{}\n\n", review.getCourse());
    }
}
