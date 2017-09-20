package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.DemoApplication;
import com.practice.jpa.hibernate.demo.entity.Course;
import com.practice.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void findAll_basic() {
        List resultList = em.createQuery("Select c From Course c").getResultList();
        logger.info("Select c From Course c -> {}", resultList);
    }

    @Test
    public void findAll_typed() {
        List<Course> resultList = em.createQuery("Select c From Course c", Course.class).getResultList();
        logger.info("Select c From Course c TYPED -> {}", resultList);
    }

    @Test
    public void findAll_namedQuery(){
        List<Course> resultList = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
        logger.info("NAMED QUERY -> {}", resultList);
    }

    @Test
    public void findById_basic() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 steps'", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c LIKE %100 steps -> {}", resultList);
    }

//    SELECT * FROM COURSE
//    WHERE COURSE.ID NOT IN (SELECT COURSE_ID  FROM STUDENT_COURSE)
    @Test
    public void jpql_courses_without_courses() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c WHERE c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c WHERE c.students is empty -> \n{}\n", resultList);
    }

    @Test
    public void jpql_courses_with_atleast_2_students() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c WHERE size(c.students) >=2", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c WHERE size(c.students) >=2 -> \n{}\n", resultList);
    }

    @Test
    public void jpql_courses_with_ordered_by_students() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c order by size(c.students)", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c ordered by size(c.students) -> \n{}\n", resultList);
    }

    @Test
    public void jpql_courses_with_ordered_by_students_desc() {
        TypedQuery<Course> query = em.createQuery("Select c From Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select c From Course c ordered by size(c.students) desc -> \n{}\n", resultList);
    }

    @Test
    public void jpql_students_with_passport_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("Select s From Student s where s.passport.number " +
                "like '%1234%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Select s From Student s where s.passport.number like '%1234%' -> \n{}\n", resultList);
    }

    //also supports:
    //like
    //between 100 and 2000
    //is null
    //upper, lower, trim, lenghts

    //join => Select c, s from Course c JOIN c.students s //normal join = inner join
    //left join => Select c, s from Course c LEFT JOIN c.students s
    //cross join => Select c, s from Course c, Student s     //3 and 4 => 3*4=12 rows

    @Test
    public void join(){
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Select c, s from Course c JOIN c.students s -> \n{}\n", resultList.size());
        for(Object[] result: resultList){
            logger.info("Course{}\nStudent{}\n", result[0], result[1]);
        }
    }

    @Test
    public void left_join(){
        Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Select c, s from Course c LEFT JOIN c.students s -> \n{}\n", resultList.size());
        for(Object[] result: resultList){
            logger.info("Course{}\nStudent{}\n", result[0], result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query = em.createQuery("Select c, s from Course c, Student s"); //3 courses * 3 students = 9 rows
        List<Object[]> resultList = query.getResultList();
        logger.info("SSelect c, s from Course c, Student s -> \n{}\n", resultList.size());
        for(Object[] result: resultList){
            logger.info("Course{}\nStudent{}\n", result[0], result[1]);
        }
    }
}
