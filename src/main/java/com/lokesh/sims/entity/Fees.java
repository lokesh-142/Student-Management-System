package com.lokesh.sims.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="fees")
public class Fees {
    @Id @Column(name="sid") private Integer sid;
    @Column(name="total_fee") private Double totalFee;
    @Column(name="paid_fee") private Double paidFee;
    @Column(name="pending_fee") private Double pendingFee;
    @Column(name="payment_date") private LocalDate paymentDate;
    private String status;
    public Integer getSid(){return sid;} public void setSid(Integer v){sid=v;}
    public Double getTotalFee(){return totalFee;} public void setTotalFee(Double v){totalFee=v;}
    public Double getPaidFee(){return paidFee;} public void setPaidFee(Double v){paidFee=v;}
    public Double getPendingFee(){return pendingFee;} public void setPendingFee(Double v){pendingFee=v;}
    public LocalDate getPaymentDate(){return paymentDate;} public void setPaymentDate(LocalDate v){paymentDate=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
}