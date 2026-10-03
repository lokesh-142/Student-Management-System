# SIMS — Spring Boot Web Application

This is a responsive Spring Boot + Thymeleaf rewrite of the original menu-driven Java/MySQL Student Information Management System.

## Features
- Student registration, login and profile
- Course registration and registered-course view
- Marks, credits, attendance and fee status
- Admin login and dashboard
- Student CRUD
- Course management
- Academic record management
- Responsive modern dashboard UI
- MySQL + Spring Data JPA/Hibernate

## Technology
Java 21, Spring Boot 3.5.6, Spring MVC, Thymeleaf, Spring Data JPA/Hibernate, MySQL, HTML5, CSS3 and JavaScript.

## Run
1. Start MySQL.
2. Set your MySQL username/password in `src/main/resources/application.properties`.
3. If necessary, run `src/main/resources/db/sims-schema.sql` in MySQL Workbench. Preserve an existing `sims` database if it already contains your data.
4. Run `mvn spring-boot:run`.
5. Open `http://localhost:8080`.

## Repository
This repository contains the Spring Boot website version of the original SIMS project.

## Security note
The supplied original application stores passwords as plain text. This rewrite retains that database behavior for compatibility. For production, use Spring Security and BCrypt password hashing.
