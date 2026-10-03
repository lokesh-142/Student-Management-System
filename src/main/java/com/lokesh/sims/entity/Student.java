package com.lokesh.sims.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="student")
public class Student {
    @Id @Column(name="sid") private Integer sid;
    private String name;
    @Column(name="father_name") private String fatherName;
    @Column(name="mother_name") private String motherName;
    private String gender;
    private LocalDate dob;
    private String email;
    private String phone;
    private String address;
    private String department;
    @Column(name="year") private Integer year;
    private String username;
    private String password;
    public Integer getSid(){return sid;} public void setSid(Integer v){sid=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getFatherName(){return fatherName;} public void setFatherName(String v){fatherName=v;}
    public String getMotherName(){return motherName;} public void setMotherName(String v){motherName=v;}
    public String getGender(){return gender;} public void setGender(String v){gender=v;}
    public LocalDate getDob(){return dob;} public void setDob(LocalDate v){dob=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
    public Integer getYear(){return year;} public void setYear(Integer v){year=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
}