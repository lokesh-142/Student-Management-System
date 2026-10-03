package com.lokesh.sims.repository;
import com.lokesh.sims.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface StudentCourseRepository extends JpaRepository<StudentCourse,StudentCourseId>{
    @Query("select c from Course c where c.courseId in (select sc.courseId from StudentCourse sc where sc.sid=:sid)")
    List<Course> findCoursesForStudent(@Param("sid") Integer sid);
    boolean existsBySidAndCourseId(Integer sid,Integer courseId);
}