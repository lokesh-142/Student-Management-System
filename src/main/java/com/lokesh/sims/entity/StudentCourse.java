package com.lokesh.sims.entity;

import jakarta.persistence.*;

@Entity
@Table(name="student_courses")
@IdClass(StudentCourseId.class)
public class StudentCourse {
    @Id private Integer sid;
    @Id @Column(name="course_id") private Integer courseId;
    public Integer getSid(){return sid;} public void setSid(Integer v){sid=v;}
    public Integer getCourseId(){return courseId;} public void setCourseId(Integer v){courseId=v;}
}