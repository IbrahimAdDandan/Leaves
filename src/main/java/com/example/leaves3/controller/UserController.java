/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.controller;

import com.example.leaves3.domain.User;
import com.example.leaves3.service.UserDetailsImp;
import com.example.leaves3.service.UserService;
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
@RequestMapping("/users")
@Secured("ADMIN")
public class UserController {
    
    @Autowired
    private UserService us;
    
    @RequestMapping("/")
    public String list(Authentication authentication, Model model) {
        model.addAttribute("users", us.getUsers());
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "users/users";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        us.remove(id);
        
        return "redirect:/users/";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(Authentication authentication, Model model, @PathVariable Long id) {
        model.addAttribute("user", us.getUserById(id));
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "users/editUser";
    }
    
    @RequestMapping("/create")
    public String create(Authentication authentication, Model model) {
        model.addAttribute("user", new User());
        UserDetailsImp userDetails = (UserDetailsImp)authentication.getPrincipal();
        String username = userDetails.getUsername();
        Long userId = userDetails.getUserid();
        String userRole = userDetails.getAuthorities().toArray()[0].toString();
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);
        model.addAttribute("role", userRole);
        return "users/createUser";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user) {
        System.out.println(user.toString());
        User u = us.add(user);
        return "redirect:/users/";   
    }
    
}
