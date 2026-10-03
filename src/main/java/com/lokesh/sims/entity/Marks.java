package com.lokesh.sims.entity;

import jakarta.persistence.*;

@Entity
@Table(name="marks")
public class Marks {
    @Id @Column(name="sid") private Integer sid;
    private Integer java;
    @Column(name="adv_java") private Integer advJava;
    @Column(name="sql_marks") private Integer sqlMarks;
    private Integer dbms;
    private Integer python;
    @Column(name="web_tech") private Integer webTech;
    public Integer getSid(){return sid;} public void setSid(Integer v){sid=v;}
    public Integer getJava(){return java;} public void setJava(Integer v){java=v;}
    public Integer getAdvJava(){return advJava;} public void setAdvJava(Integer v){advJava=v;}
    public Integer getSqlMarks(){return sqlMarks;} public void setSqlMarks(Integer v){sqlMarks=v;}
    public Integer getDbms(){return dbms;} public void setDbms(Integer v){dbms=v;}
    public Integer getPython(){return python;} public void setPython(Integer v){python=v;}
    public Integer getWebTech(){return webTech;} public void setWebTech(Integer v){webTech=v;}
    public int total(){return n(java)+n(advJava)+n(sqlMarks)+n(dbms)+n(python)+n(webTech);}
    public double percentage(){return total()/6.0;}
    private int n(Integer x){return x==null?0:x;}
}