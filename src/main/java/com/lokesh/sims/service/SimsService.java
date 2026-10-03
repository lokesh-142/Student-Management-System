package com.lokesh.sims.service;

import com.lokesh.sims.entity.*;
import com.lokesh.sims.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class SimsService {
    private final StudentRepository students;
    private final CourseRepository courses;
    private final MarksRepository marks;
    private final CreditsRepository credits;
    private final AttendanceRepository attendance;
    private final FeesRepository fees;
    private final AdminRepository admins;
    private final StudentCourseRepository studentCourses;

    public SimsService(StudentRepository students, CourseRepository courses, MarksRepository marks,
                       CreditsRepository credits, AttendanceRepository attendance, FeesRepository fees,
                       AdminRepository admins, StudentCourseRepository studentCourses) {
        this.students=students; this.courses=courses; this.marks=marks; this.credits=credits;
        this.attendance=attendance; this.fees=fees; this.admins=admins; this.studentCourses=studentCourses;
    }

    public Optional<Student> loginStudent(String username,String password){ return students.findByUsernameAndPassword(username,password); }
    public Optional<Admin> loginAdmin(String username,String password){ return admins.findByUsernameAndPassword(username,password); }
    public List<Student> allStudents(){return students.findAll();}
    public Optional<Student> student(int id){return students.findById(id);}
    public List<Course> allCourses(){return courses.findAll();}
    public List<Course> registeredCourses(int sid){return studentCourses.findCoursesForStudent(sid);}

    @Transactional
    public void registerCourse(int sid,int courseId){
        if(!studentCourses.existsBySidAndCourseId(sid,courseId)){
            StudentCourse sc=new StudentCourse();
            sc.setSid(sid); sc.setCourseId(courseId);
            studentCourses.save(sc);
        }
    }

    @Transactional
    public Student saveStudent(Student student){
        if(student.getSid()==null) throw new IllegalArgumentException("Student ID is required to preserve the original SIMS schema.");
        return students.save(student);
    }

    @Transactional
    public Student registerStudent(Student student){
        String username = student.getUsername() == null ? "" : student.getUsername().trim();
        String email = student.getEmail() == null ? "" : student.getEmail().trim();

        if(username.isBlank() || student.getPassword() == null || student.getPassword().isBlank())
            throw new IllegalArgumentException("Username and password are required.");
        if(students.existsByUsername(username))
            throw new IllegalArgumentException("Username is already registered.");
        if(!email.isBlank() && students.findByEmail(email).isPresent())
            throw new IllegalArgumentException("Email is already registered.");

        int nextId = students.findAll().stream()
                .map(Student::getSid).filter(Objects::nonNull)
                .max(Integer::compareTo).orElse(0) + 1;

        student.setSid(nextId);
        student.setUsername(username);
        student.setEmail(email.isBlank() ? null : email);
        return students.save(student);
    }

    @Transactional
    public void deleteStudent(int id){
        marks.deleteById(id); credits.deleteById(id); attendance.deleteById(id); fees.deleteById(id);
        students.deleteById(id);
    }

    public Optional<Marks> marks(int id){return marks.findById(id);}
    public Optional<Credits> credits(int id){return credits.findById(id);}
    public Optional<Attendance> attendance(int id){return attendance.findById(id);}
    public Optional<Fees> fees(int id){return fees.findById(id);}
    @Transactional public Course saveCourse(Course course){return courses.save(course);}
    @Transactional public void deleteCourse(int id){courses.deleteById(id);}

    public Map<String,Object> dashboard(){
        Map<String,Object> data=new LinkedHashMap<>();
        data.put("students",students.count()); data.put("courses",courses.count());
        data.put("marks",marks.count()); data.put("attendance",attendance.count());
        return data;
    }
}