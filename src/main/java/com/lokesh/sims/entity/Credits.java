package com.lokesh.sims.entity;

import jakarta.persistence.*;

@Entity
@Table(name="credits")
public class Credits {
    @Id @Column(name="sid") private Integer sid;
    @Column(name="java_credit") private Integer javaCredit;
    @Column(name="adv_java_credit") private Integer advJavaCredit;
    @Column(name="sql_credit") private Integer sqlCredit;
    @Column(name="dbms_credit") private Integer dbmsCredit;
    @Column(name="python_credit") private Integer pythonCredit;
    @Column(name="web_credit") private Integer webCredit;

    public Integer getSid(){return sid;} public void setSid(Integer v){sid=v;}
    public Integer getJavaCredit(){return javaCredit;} public void setJavaCredit(Integer v){javaCredit=v;}
    public Integer getAdvJavaCredit(){return advJavaCredit;} public void setAdvJavaCredit(Integer v){advJavaCredit=v;}
    public Integer getSqlCredit(){return sqlCredit;} public void setSqlCredit(Integer v){sqlCredit=v;}
    public Integer getDbmsCredit(){return dbmsCredit;} public void setDbmsCredit(Integer v){dbmsCredit=v;}
    public Integer getPythonCredit(){return pythonCredit;} public void setPythonCredit(Integer v){pythonCredit=v;}
    public Integer getWebCredit(){return webCredit;} public void setWebCredit(Integer v){webCredit=v;}
    public int total(){return sum(javaCredit,advJavaCredit,sqlCredit,dbmsCredit,pythonCredit,webCredit);}
    private int sum(Integer... v){int s=0;for(Integer x:v)s+=x==null?0:x;return s;}
}