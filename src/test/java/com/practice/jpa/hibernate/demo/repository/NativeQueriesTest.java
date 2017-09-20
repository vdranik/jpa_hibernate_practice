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
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NativeQueriesTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void native_queries_basic() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE");
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE -> {}", resultList);
    }
    @Test
    public void native_queries_with_isdeleted() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE is_deleted=0");
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE WHERE is_deleted=0 -> {}", resultList);
    }


    @Test
    public void native_queries_typed() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE TYPED -> {}", resultList);
    }

    @Test
    public void native_queries_typed_param() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=?", Course.class);
        query.setParameter(1, 10001L);
        List<Course> resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE TYPED WITH PARAM -> {}", resultList);
    }

    @Test
    public void native_queries_typed_named_param() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id= :id", Course.class);
        query.setParameter("id", 10001L);
        List<Course> resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE TYPED WITH NAMED PARAM -> {}", resultList);
    }

    @Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("UPDATE COURSE SET last_updated_date=sysdate()", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
    }
}
