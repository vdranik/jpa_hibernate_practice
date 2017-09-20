package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.DemoApplication;
import com.practice.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CriteriaQueryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void criteria_basic() {
        //Select c From Course c

        //use CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //root for table
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("TypedQuery -> \n{}\n", resultList);
    }

    @Test
    public void criteria_all_courses_having_100Steps() {
        //"Select c From Course c WHERE name like '%100 Steps'"

        //use CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //root for table
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //define predicates
        Predicate likePredicate = criteriaBuilder.like(courseRoot.get("name"),"%100 steps");
        criteriaQuery.where(likePredicate);


        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("TypedQuery with LIKE-> \n{}\n", resultList);
    }

    @Test
    public void criteria_all_courses_without_students() {
        //"Select c From Course c WHERE c.students is empty"

        //use CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //root for table
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //define predicates
        Predicate emptyPredicate = criteriaBuilder.isEmpty(courseRoot.get("students"));
        criteriaQuery.where(emptyPredicate);

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("TypedQuery with EMPTY-> \n{}\n", resultList);
    }

    @Test
    public void criteria_join() {
        //"Select c From Course c JOIN c.students s"

        //use CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //root for table
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //use JOIN
        courseRoot.join("students");

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("TypedQuery with JOIN-> \n{}\n", resultList);
    }

    @Test
    public void criteria_left_join() {
        //"Select c From Course c LEFT JOIN c.students s"

        //use CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);

        //root for table
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        //use LEFT JOIN
        courseRoot.join("students", JoinType.LEFT);

        TypedQuery<Course> query = em.createQuery(criteriaQuery.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("TypedQuery with LEFT JOIN-> \n{}\n", resultList);
    }

}
