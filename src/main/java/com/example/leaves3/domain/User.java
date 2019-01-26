/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ibrahim
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String full_name;
    @Column( unique = true, nullable = false )
    private String username;
    @Column( nullable = false )
    private String password;
    private String role_name;
    private String email;
    private int phone;
    private boolean isEnabled = true;
    @OneToMany( mappedBy = "user" )
    private List<Leaves> leaves;


    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    
    public List<Leaves> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leaves> leaves) {
        this.leaves = leaves;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", full_name=" + full_name + ", username=" + username + ", password=" + password + ", role_name=" + role_name + ", email=" + email + ", phone=" + phone + '}';
    }

    public User(Long id, String full_name, String username, String password, String role_name, String email, int phone) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.role_name = role_name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    public User() {
    }
    
    
}
