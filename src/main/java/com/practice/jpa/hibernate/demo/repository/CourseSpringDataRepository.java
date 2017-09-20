package com.practice.jpa.hibernate.demo.repository;

import com.practice.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// http://localhost:8080/courses
// http://localhost:8080/courses/10001
@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Long id);
    long countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);

    @Query("Select c From Course c where name like '%100 steps'")
    List<Course> courseWith100StepsInName(String name);

    @Query(value = "Select * From Course c where name like '%100 steps'", nativeQuery = true)
    List<Course> courseWith100StepsInNameUsingNativeQuery(String name);

    @Query(name = "query_get_all_courses_100_Step_courses")
    List<Course> courseWith100StepsInNameUsingNamedQuery(String name);

}
