/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.controller;

import com.example.leaves3.domain.LeaveType;
import com.example.leaves3.service.Leave_type_service;
import com.example.leaves3.service.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ibrahim
 */

@Controller
@RequestMapping("/leavetype")
@Secured("MANAGER")
public class LeaveTypeController {
    
    @Autowired
    Leave_type_service lts;
    
    @RequestMapping("/")
    public String home(Authentication authentication, Model model) {
        Iterable<LeaveType> leave_type = lts.getLeave_type();
        model.addAttribute("listLeaves", leave_type);
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leavetype/list";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(Authentication authentication, Model model, @PathVariable Long id) {
        model.addAttribute("leave", lts.getLeave(id));
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leavetype/editLeave";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        lts.remove(id);
        
        return "redirect:/leavetype/";
    }
    
    @RequestMapping("/create")
    public String create(Authentication authentication, Model model) {
        model.addAttribute("leave", new LeaveType());
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leavetype/createLeave";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(LeaveType leave) {
        System.out.println(leave.toString());
        LeaveType lt = lts.add(leave);
        return "redirect:/leavetype/";   
    }
}
