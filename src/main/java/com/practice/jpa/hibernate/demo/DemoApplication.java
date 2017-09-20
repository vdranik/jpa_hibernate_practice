package com.practice.jpa.hibernate.demo;

import com.practice.jpa.hibernate.demo.repository.CourseRepository;
import com.practice.jpa.hibernate.demo.repository.EmployeeRepository;
import com.practice.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
//		Course course = courseRepository.findById(10001L);
//		logger.info("Course 10001 -> {}", course);
//		//courseRepository.deleteById(10002L);
//        courseRepository.save(new Course("Microservices in 100 steps"));

       // courseRepository.playWithEntityManager();

		//studentRepository.saveStudentWithPassport();

		//courseRepository.addReviewsHardcodedForCourse();

//		List<Review> reviews = new ArrayList<>();
//		Review review1 = new Review("5", "Great Hands-on Stuff");
//		Review review2 = new Review("5", "Hatsoff");
//		reviews.add(review1);
//		reviews.add(review2);
//
//		courseRepository.addReviewsForCourse(10003L, reviews);

		//studentRepository.insertHardcodedStudentAndCourse();

//		Student student = new Student("Jack");
//		Course course = new Course("Microservices in 100 steps");
//		studentRepository.insertStudentAndCourse(student, course);

//		Employee employee1 = new FullTimeEmployee("Jack", new BigDecimal(10000));
//		Employee employee2 = new PartTimeEmployee("Jill", new BigDecimal(50));
//
//
//		employeeRepository.insert(employee1);
//		employeeRepository.insert(employee2);
//
//		//logger.info("All Employees -> \n{}\n",  employeeRepository.retrieveAllEmployees());
//		logger.info("All Employees -> \n{}\n",  employeeRepository.retrieveAllPartTimeEmployees());

		//courseRepository.deleteById(10001L);

	}
}
