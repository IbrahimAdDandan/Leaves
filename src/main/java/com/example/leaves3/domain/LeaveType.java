/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ibrahim
 */

@Entity
public class LeaveType implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name;
    private String description;
    private int number_of_hours;
    
    @OneToMany( mappedBy = "leave_type")
    private List<Leaves> leaves;

    public List<Leaves> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leaves> leaves) {
        this.leaves = leaves;
    }
    
    public LeaveType() {
        
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Leave_type{" + "id=" + id + ", name=" + name + ", description=" + description + ", number_of_hours=" + number_of_hours + '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumber_of_hours(int number_of_hours) {
        this.number_of_hours = number_of_hours;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber_of_hours() {
        return number_of_hours;
    }
    
    public LeaveType(Long id, String name, String description, int number_of_hours) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.number_of_hours = number_of_hours;
    }
    
    
    
}
