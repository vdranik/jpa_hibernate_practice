package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.DemoApplication;
import com.practice.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository repository;

    @Test
    public void findById_CoursePresent(){
        Optional<Course> courseOptional = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
        logger.info("findById_CoursePresent -> \n{}\n", courseOptional.isPresent());
    }

    @Test
    public void findById_CourseNotPresent(){
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
        logger.info("findById_CourseNotPresent -> \n{}\n", courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository(){
//        Course course = new Course("Microservices in 100 Steps");
//        repository.save(course);
//        course.setName("Microservices in 100 Steps - UPDATED");
//        repository.save(course);
        logger.info("Courses -> \n{}\n", repository.findAll());
        logger.info("Count -> \n{}\n", repository.count());
    }

    @Test
    public void sort(){
        Sort sort = new Sort(Sort.Direction.DESC, "name"); //.and(sort) ...
        logger.info("SORTED Courses -> \n{}\n", repository.findAll(sort));
    }

    @Test
    public void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> \n{}\n", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page -> \n{}\n", secondPage.getContent());
    }

    @Test
    public void findUsingName(){
        logger.info("FindByName -> {} ", repository.findByName("JPA in 50 steps")); //we added in interface the method findByName
    }
}
