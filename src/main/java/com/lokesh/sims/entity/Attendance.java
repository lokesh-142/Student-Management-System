package com.lokesh.sims.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id @Column(name="sid") private Integer sid;
    @Column(name="java_att") private Double javaAtt;
    @Column(name="adv_java_att") private Double advJavaAtt;
    @Column(name="sql_att") private Double sqlAtt;
    @Column(name="dbms_att") private Double dbmsAtt;
    @Column(name="python_att") private Double pythonAtt;
    @Column(name="web_att") private Double webAtt;

    public Integer getSid(){return sid;} public void setSid(Integer v){sid=v;}
    public Double getJavaAtt(){return javaAtt;} public void setJavaAtt(Double v){javaAtt=v;}
    public Double getAdvJavaAtt(){return advJavaAtt;} public void setAdvJavaAtt(Double v){advJavaAtt=v;}
    public Double getSqlAtt(){return sqlAtt;} public void setSqlAtt(Double v){sqlAtt=v;}
    public Double getDbmsAtt(){return dbmsAtt;} public void setDbmsAtt(Double v){dbmsAtt=v;}
    public Double getPythonAtt(){return pythonAtt;} public void setPythonAtt(Double v){pythonAtt=v;}
    public Double getWebAtt(){return webAtt;} public void setWebAtt(Double v){webAtt=v;}
    public double average(){return (v(javaAtt)+v(advJavaAtt)+v(sqlAtt)+v(dbmsAtt)+v(pythonAtt)+v(webAtt))/6.0;}
    private double v(Double x){return x==null?0:x;}
}