package com.lokesh.sims.repository;
import com.lokesh.sims.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course,Integer>{
    boolean existsByCourseNameIgnoreCase(String courseName);
}