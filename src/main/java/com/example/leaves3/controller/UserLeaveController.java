/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.controller;

import com.example.leaves3.domain.LeaveType;
import com.example.leaves3.domain.Leaves;
import com.example.leaves3.service.LeaveService;
import com.example.leaves3.service.Leave_type_service;
import com.example.leaves3.service.MailService;
import com.example.leaves3.service.UserDetailsImp;
import com.example.leaves3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ibrahim
 */

@Controller
@Secured("USER")
public class UserLeaveController {
    
    @Autowired
    private UserService us;
    
    @Autowired
    private LeaveService ls;
    
    @Autowired
    private Leave_type_service lts;
    
    @Autowired
    private MailService mailService;
    
    @RequestMapping("/myleaves")
    @Secured("USER")
    public String myLeaves(Authentication authentication, Model model) {
        
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        Iterable<Leaves> myleaves = us.getUserLeaves(userId);
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("myLeaves", myleaves);
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leaves/myLeaves";
    }
    
    @RequestMapping("/reqLeave")
    @Secured("USER")
    public String reqLeave(Authentication authentication, Model model) {
        
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        Iterable<LeaveType> leavesTypes = lts.getLeave_type();
        model.addAttribute("leavesTypes", leavesTypes);
        model.addAttribute("leave", new Leaves());
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leaves/reqLeave";
    }
  
    @RequestMapping(value = "/savemyleaves", method = RequestMethod.POST)
    @Secured("USER")
    public String save(Leaves leave, Authentication authentication) {
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String userMail = userDetails.getUSerEmail();
        ls.add(leave);
        mailService.senMail(userMail);
        return "redirect:/myleaves";   
    }
    
    
}
