package com.lokesh.sims.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    public Integer getId(){return id;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
}