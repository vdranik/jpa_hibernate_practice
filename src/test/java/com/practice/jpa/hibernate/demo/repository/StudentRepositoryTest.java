package com.practice.jpa.hibernate.demo.repository;


import com.practice.jpa.hibernate.demo.DemoApplication;
import com.practice.jpa.hibernate.demo.entity.Address;
import com.practice.jpa.hibernate.demo.entity.Course;
import com.practice.jpa.hibernate.demo.entity.Passport;
import com.practice.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void contextLoads() {
        logger.info("Testing is running!");
    }

//    @Test
//    @Transactional
//    public void findStudentById_basic() {
//        Student student = em.find(Student.class, 20001L);
//        logger.info("student -> {}", student);
//        logger.info("passport -> {}", student.getPassport());
//       // assertEquals("JPA in 50 steps", student.getName());
//    }

//    @Test
//    public void soumeDummyOperation(){
//        studentRepository.someDummyOperation();
//    }

    @Test
    @Transactional
    public void retrievePassportAndAssociateStudent(){
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("passport -> {}", passport);
        logger.info("student -> {}", passport.getStudent());
    }

    @Test
    @Transactional
    public void retrieveStudentAndAssociatePassport(){
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses(){
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> \n\n{}\n\n", student);
        logger.info("courses -> \n\n{}\n\n", student.getCourses());
    }

    @Test
    @Transactional
    public void retrieveCoursesAndStudents(){
        Course course = em.find(Course.class, 10001L);
        logger.info("\ncourse -> \n{}\n", course);
        logger.info("\nstudents -> \n{}\n", course.getStudents());
    }

    @Test
    @Transactional
    public void setAddressDetails(){
        Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("line1", "line2", "Kyiv"));
        em.flush();
        logger.info("\nstudent -> \n{}\n", student);
        logger.info("\npassport -> \n{}\n",student.getPassport());
    }


}
