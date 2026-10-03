package com.lokesh.sims.controller;

import com.lokesh.sims.service.SimsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final SimsService service;
    public AuthController(SimsService service) { this.service = service; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login/student")
    public String studentLogin(@RequestParam String username, @RequestParam String password,
                               HttpSession session, Model model) {
        return service.loginStudent(username, password)
                .map(student -> {
                    session.setAttribute("studentId", student.getSid());
                    session.setAttribute("role", "STUDENT");
                    return "redirect:/student/dashboard";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid username or password");
                    return "login";
                });
    }

    @PostMapping("/login/admin")
    public String adminLogin(@RequestParam String username, @RequestParam String password,
                             HttpSession session, Model model) {
        return service.loginAdmin(username, password)
                .map(admin -> {
                    session.setAttribute("role", "ADMIN");
                    return "redirect:/admin/dashboard";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid admin credentials");
                    return "login";
                });
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}