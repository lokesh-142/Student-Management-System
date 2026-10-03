CREATE DATABASE IF NOT EXISTS sims;
USE sims;

CREATE TABLE IF NOT EXISTS student (
 sid INT PRIMARY KEY, name VARCHAR(100), father_name VARCHAR(100), mother_name VARCHAR(100), gender VARCHAR(20), dob DATE,
 email VARCHAR(120), phone VARCHAR(20), address VARCHAR(255), department VARCHAR(100), year INT, username VARCHAR(80) UNIQUE, password VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS admin (id INT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(80) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL);
CREATE TABLE IF NOT EXISTS courses (course_id INT PRIMARY KEY AUTO_INCREMENT, course_name VARCHAR(100) UNIQUE NOT NULL, credits INT NOT NULL);
CREATE TABLE IF NOT EXISTS student_courses (sid INT NOT NULL, course_id INT NOT NULL, PRIMARY KEY(sid,course_id), FOREIGN KEY(sid) REFERENCES student(sid) ON DELETE CASCADE, FOREIGN KEY(course_id) REFERENCES courses(course_id) ON DELETE CASCADE);
CREATE TABLE IF NOT EXISTS marks (sid INT PRIMARY KEY, java INT, adv_java INT, sql_marks INT, dbms INT, python INT, web_tech INT, FOREIGN KEY(sid) REFERENCES student(sid) ON DELETE CASCADE);
CREATE TABLE IF NOT EXISTS credits (sid INT PRIMARY KEY, java_credit INT, adv_java_credit INT, sql_credit INT, dbms_credit INT, python_credit INT, web_credit INT, FOREIGN KEY(sid) REFERENCES student(sid) ON DELETE CASCADE);
CREATE TABLE IF NOT EXISTS attendance (sid INT PRIMARY KEY, java_att DECIMAL(5,2), adv_java_att DECIMAL(5,2), sql_att DECIMAL(5,2), dbms_att DECIMAL(5,2), python_att DECIMAL(5,2), web_att DECIMAL(5,2), FOREIGN KEY(sid) REFERENCES student(sid) ON DELETE CASCADE);
CREATE TABLE IF NOT EXISTS fees (sid INT PRIMARY KEY, total_fee DECIMAL(10,2), paid_fee DECIMAL(10,2), pending_fee DECIMAL(10,2), payment_date DATE, status VARCHAR(20), FOREIGN KEY(sid) REFERENCES student(sid) ON DELETE CASCADE);

-- Example: INSERT INTO admin(username,password) VALUES ('admin','admin123');