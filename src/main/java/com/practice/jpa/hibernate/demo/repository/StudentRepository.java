package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.entity.Course;
import com.practice.jpa.hibernate.demo.entity.Passport;
import com.practice.jpa.hibernate.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public Student findById(Long id){
        return em.find(Student.class, id);
    }

    public void deleteById(Long id){
        Student student = findById(id);
        em.remove(student);
    }

    public Student save(Student student){
        if(student.getId() == null){
            //insert
            em.persist(student);
        } else {
            //update
            em.merge(student);
        }

        return student;
    }

    public void saveStudentWithPassport(){

        Passport passport = new Passport("Z987654");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);

        //em.flush();

    }

    @Transactional
    public void someDummyOperation(){
        //DB operation 1 - retrieve student
        Student student = em.find(Student.class, 20001L);
        //Persistance Context (student)

        //DB operation 2 - retrieve passport
        Passport passport = student.getPassport();
        //Persistance Context(student, passport)

        //DB operation 3 - update passport
        passport.setNumber("E123456");
        //Persistance Context(student, passport++)

        //DB operation 4 - update student
        student.setName("RANGA - UPDATED");
        //Persistance Context(student++, passport++)
    }

    public void insertHardcodedStudentAndCourse(){
        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 steps");
        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course){
        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
        em.persist(course);
    }
}
