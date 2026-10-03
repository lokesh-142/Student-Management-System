package com.lokesh.sims.controller;

import com.lokesh.sims.entity.*;
import com.lokesh.sims.service.SimsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    private final SimsService service;
    public WebController(SimsService service) { this.service = service; }

    @GetMapping("/")
    public String home() { return "redirect:/login"; }

    @GetMapping("/student/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("studentId");
        if (id == null) return "redirect:/login";
        Student student = service.student(id).orElseThrow();
        model.addAttribute("student", student);
        model.addAttribute("marks", service.marks(id).orElse(null));
        model.addAttribute("attendance", service.attendance(id).orElse(null));
        model.addAttribute("fees", service.fees(id).orElse(null));
        model.addAttribute("courses", service.allCourses());
        model.addAttribute("registeredCourses", service.registeredCourses(id));
        return "student-dashboard";
    }

    @GetMapping("/student/profile")
    public String profile(HttpSession session, Model model) {
        Integer id = (Integer) session.getAttribute("studentId");
        if (id == null) return "redirect:/login";
        model.addAttribute("student", service.student(id).orElseThrow());
        return "profile";
    }

    @PostMapping("/student/profile")
    public String updateProfile(HttpSession session, Student form) {
        Integer id = (Integer) session.getAttribute("studentId");
        Student student = service.student(id).orElseThrow();
        student.setName(form.getName());
        student.setFatherName(form.getFatherName());
        student.setMotherName(form.getMotherName());
        student.setEmail(form.getEmail());
        student.setPhone(form.getPhone());
        student.setAddress(form.getAddress());
        student.setDepartment(form.getDepartment());
        student.setYear(form.getYear());
        service.saveStudent(student);
        return "redirect:/student/profile?updated";
    }

    @PostMapping("/student/register-course")
    public String registerCourse(@RequestParam int courseId, HttpSession session) {
        Integer sid = (Integer) session.getAttribute("studentId");
        if (sid == null) return "redirect:/login";
        service.registerCourse(sid, courseId);
        return "redirect:/student/dashboard?registered";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/login";
        model.addAllAttributes(service.dashboard());
        model.addAttribute("students", service.allStudents());
        model.addAttribute("courses", service.allCourses());
        return "admin-dashboard";
    }

    @GetMapping("/admin/students")
    public String students(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/login";
        model.addAttribute("students", service.allStudents());
        return "students";
    }

    @GetMapping("/admin/students/new")
    public String newStudent(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/login";
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/admin/students/save")
    public String saveStudent(Student student) {
        service.saveStudent(student);
        return "redirect:/admin/students?success";
    }

    @PostMapping("/admin/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "redirect:/admin/students?deleted";
    }

    @GetMapping("/admin/courses")
    public String courses(HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/login";
        model.addAttribute("courses", service.allCourses());
        model.addAttribute("course", new Course());
        return "courses";
    }

    @PostMapping("/admin/courses/save")
    public String saveCourse(Course course) {
        service.saveCourse(course);
        return "redirect:/admin/courses?success";
    }

    @PostMapping("/admin/courses/delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        service.deleteCourse(id);
        return "redirect:/admin/courses?deleted";
    }

    @GetMapping("/admin/records/{id}")
    public String records(@PathVariable int id, HttpSession session, Model model) {
        if (!"ADMIN".equals(session.getAttribute("role"))) return "redirect:/login";
        model.addAttribute("student", service.student(id).orElseThrow());
        model.addAttribute("marks", service.marks(id).orElse(null));
        model.addAttribute("credits", service.credits(id).orElse(null));
        model.addAttribute("attendance", service.attendance(id).orElse(null));
        model.addAttribute("fees", service.fees(id).orElse(null));
        return "student-records";
    }
}