/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.config;

import com.example.leaves3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 *
 * @author ibrahim
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;
    
    @Autowired
    public void configAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register*")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/bootstrap.min.css")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/register/register*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
    
}
