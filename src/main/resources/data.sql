INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10001, 'JPA in 50 steps', sysdate(), sysdate(), FALSE ); --it used in tests
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10002, 'Spring in 50 steps', sysdate(), sysdate(), FALSE);
INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10003, 'Spring Boot in 100 steps', sysdate(), sysdate(), FALSE);
-- INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10004, 'Dummy1', sysdate(), sysdate(), FALSE);
-- INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10005, 'Dummy2', sysdate(), sysdate(), FALSE);
-- INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10006, 'Dummy3', sysdate(), sysdate(), FALSE);
-- INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10007, 'Dummy4', sysdate(), sysdate(), FALSE);
-- INSERT INTO course(id, name, created_date, last_updated_date, is_deleted) VALUES(10008, 'Dummy5', sysdate(), sysdate(), FALSE);

INSERT INTO passport(id, number, created_date, last_updated_date) VALUES(40001, 'E123456', sysdate(), sysdate());
INSERT INTO passport(id, number, created_date, last_updated_date) VALUES(40002, 'N123476', sysdate(), sysdate());
INSERT INTO passport(id, number, created_date, last_updated_date) VALUES(40003, 'M967556', sysdate(), sysdate());

INSERT INTO student(id, name, passport_id, created_date, last_updated_date) VALUES(20001, 'Ranga', 40001, sysdate(), sysdate());
INSERT INTO student(id, name, passport_id, created_date, last_updated_date) VALUES(20002, 'Adam', 40002, sysdate(), sysdate());
INSERT INTO student(id, name, passport_id, created_date, last_updated_date) VALUES(20003, 'Jane', 40003, sysdate(), sysdate());

-- INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50001, '5', 'GREAT COURSE', 10001,sysdate(), sysdate());
-- INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50002, '4', 'Wonderful Course', 10001, sysdate(), sysdate());
-- INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50003, '5', 'Awesome course', 10003, sysdate(), sysdate());

-- with rating ordinal enumerated
-- INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50001, 5, 'GREAT COURSE', 10001,sysdate(), sysdate());
-- INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50002, 4, 'Wonderful Course', 10001, sysdate(), sysdate());
-- INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50003, 5, 'Awesome course', 10003, sysdate(), sysdate());

-- with rating string enumerated
INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50001, 'FIVE', 'GREAT COURSE', 10001,sysdate(), sysdate());
INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50002, 'FOUR', 'Wonderful Course', 10001, sysdate(), sysdate());
INSERT INTO review(id, rating, description, course_id, created_date, last_updated_date) VALUES(50003, 'FIVE', 'Awesome course', 10003, sysdate(), sysdate());

-- SELECT * FROM student_course , student , course
-- WHERE
--   student_course.course_id = course.id
--   AND
--   student_course.student_id =  student.id

INSERT INTO student_course(student_id, course_id) VALUES (20001,10001);
INSERT INTO student_course(student_id, course_id) VALUES (20002,10001);
INSERT INTO student_course(student_id, course_id) VALUES (20003,10001);
INSERT INTO student_course(student_id, course_id) VALUES (20001,10003);






