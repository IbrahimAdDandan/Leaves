/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ibrahim
 */

@Entity
public class Leaves {
    
    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date end_date;
    @Column(nullable = true)
    private boolean approval;
    private boolean manipulated = false; 

    @ManyToOne
    private User user;
    
    @ManyToOne
    private LeaveType leave_type;

    public boolean isManipulated() {
        return manipulated;
    }

    public void setManipulated(boolean manipulated) {
        this.manipulated = manipulated;
    }
  
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LeaveType getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(LeaveType leave_type) {
        this.leave_type = leave_type;
    }
    

    public Leaves(Long id, Date start_date, Date end_date, boolean approval) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.approval = approval;
    }

    public Leaves() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    @Override
    public String toString() {
        return "Leave{" + "id=" + id + ", start_date=" + start_date + ", end_date=" + end_date + ", approval=" + approval + '}';
    }
    
}
