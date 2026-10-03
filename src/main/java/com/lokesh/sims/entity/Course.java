package com.lokesh.sims.entity;

import jakarta.persistence.*;

@Entity
@Table(name="courses")
public class Course {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="course_id") private Integer courseId;
    @Column(name="course_name") private String courseName;
    private Integer credits;
    public Integer getCourseId(){return courseId;} public void setCourseId(Integer v){courseId=v;}
    public String getCourseName(){return courseName;} public void setCourseName(String v){courseName=v;}
    public Integer getCredits(){return credits;} public void setCredits(Integer v){credits=v;}
}