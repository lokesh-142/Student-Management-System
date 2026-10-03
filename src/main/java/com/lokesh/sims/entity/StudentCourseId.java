package com.lokesh.sims.entity;

import java.io.Serializable;
import java.util.Objects;

public class StudentCourseId implements Serializable {
    private Integer sid;
    private Integer courseId;
    public StudentCourseId(){}
    public StudentCourseId(Integer sid,Integer courseId){this.sid=sid;this.courseId=courseId;}
    public boolean equals(Object o){if(this==o)return true;if(!(o instanceof StudentCourseId x))return false;return Objects.equals(sid,x.sid)&&Objects.equals(courseId,x.courseId);}
    public int hashCode(){return Objects.hash(sid,courseId);}
}