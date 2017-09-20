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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PerformanceTuningTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblem(){
        List<Course> courses = new ArrayList<>();
        courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList(); //getSinleResult();
        courses.forEach(course -> logger.info( "Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void savingNPlusOneProblem_EntityGraph(){ //increase jdbc queries to db

        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");

        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        courses.forEach(course -> logger.info( "Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph(){ //decrease jdbc queries to db

        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = entityGraph.addSubgraph("students");

        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        courses.forEach(course -> logger.info( "Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch(){ //increase jdbc queries to db
        List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();

        courses.forEach(course -> logger.info( "Course -> {} Students -> {}", course, course.getStudents()));
    }
}
