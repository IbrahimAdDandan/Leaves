/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.controller;

import com.example.leaves3.domain.Leaves;
import com.example.leaves3.service.LeaveService;
import com.example.leaves3.service.UserDetailsImp;
import java.util.ArrayList;
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
@RequestMapping("trans-leaves")
@Secured("MANAGER")
public class TransactionalLeaveController {
    
    private final LeaveService leaveService;
    ArrayList<Leaves> leaves;

    @Autowired
    public TransactionalLeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    
    
    
    @RequestMapping("/")
    @Secured("MANAGER")
    public String manageLeaves(Authentication authentication, Model model) {
//        Iterable<Leaves> leaves = ls.getNoStatusLeaves();
        this.leaves = (ArrayList) this.leaveService.getNoStatusLeaves();
        model.addAttribute("leaves", this.leaves);
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leaves/transleave";
    }
    
    @RequestMapping("/approve/{index}")
    @Secured("MANAGER")
    public String approve(@PathVariable int index, Authentication authentication, Model model) {
        
        this.leaves.get(index).setApproval(true);
        this.leaves.get(index).setManipulated(true);
        model.addAttribute("leaves", this.leaves);
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leaves/transleave";
    }
    
    @RequestMapping("/reject/{index}")
    @Secured("MANAGER")
    public String reject(@PathVariable int index, Authentication authentication, Model model) {
        this.leaves.get(index).setApproval(false);
        this.leaves.get(index).setManipulated(true);
        model.addAttribute("leaves", this.leaves);
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "leaves/transleave";
    }
    
    @RequestMapping("/execute/")
    @Secured("MANAGER")
    public String execute() {
        this.leaveService.saveAll(this.leaves);
        return "redirect:/trans-leaves/";
    }
    
}
