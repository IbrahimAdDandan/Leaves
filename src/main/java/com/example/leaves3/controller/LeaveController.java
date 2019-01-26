/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.controller;

import com.example.leaves3.service.LeaveService;
import com.example.leaves3.service.UserDetailsImp;
import com.example.leaves3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ibrahim
 */

@Controller
@RequestMapping("leaves")
@Secured("MANAGER")
public class LeaveController {
    
    private final LeaveService ls;
    private final UserService us;

    @Autowired
    public LeaveController(LeaveService ls, UserService us) {
        this.ls = ls;
        this.us = us;
    }
    
    
    
    @RequestMapping("/")
    @Secured("MANAGER")
    public String manageLeaves(Authentication authentication, Model model) {
        Iterable<Object> leaves = this.us.getNoStatusLeavesWithUSer();
//        for (Object next : leaves) {
//            System.out.println(next);
//        }
        
        model.addAttribute("leaves", leaves);
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leaves/leaves";
    }
    
    @RequestMapping("/approve/{id}")
    @Secured("MANAGER")
    public String approve(@PathVariable Long id) {
        this.ls.manipulate(id, true);
        return "redirect:/leaves/";
    }
    
    @RequestMapping("/reject/{id}")
    @Secured("MANAGER")
    public String reject(@PathVariable Long id) {
        this.ls.manipulate(id, false);
        return "redirect:/leaves/";
    }
    
    
    
}
