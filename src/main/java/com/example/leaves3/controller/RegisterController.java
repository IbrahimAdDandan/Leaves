/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.controller;

import com.example.leaves3.domain.User;
import com.example.leaves3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ibrahim
 */

@Controller
@RequestMapping("/register")
public class RegisterController {
    
    @Autowired
    private UserService us;
    
    @RequestMapping("")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String save(User user) {
        System.out.println(user.toString());
        user.setRole_name("USER");
        User u = us.add(user);
        return "redirect:/";   
    }
    
}
