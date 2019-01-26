/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ibrahim
 */

@Service
public class MailService {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String myMail;
    
    public void senMail(String email) {
        try {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        System.out.println("my Email is: " + this.myMail);
        mail.setFrom(this.myMail);
        mail.setSubject("leave request notification");
        mail.setText("You'r request registerd, you can explore the result in your leaves.");
        
        this.javaMailSender.send(mail);
        } catch (MailException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
