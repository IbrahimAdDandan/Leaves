/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.service;

import com.example.leaves3.domain.Leaves;
import com.example.leaves3.domain.User;
import com.example.leaves3.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ibrahim
 */

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
    
    public Iterable<User> getUsers() {
        return  userRepository.findAll();
    }
    
    public User get() {
        return new User();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
    
    public Iterable<Leaves> getUserLeaves(Long id) {
        User user = userRepository.findById(id).get();
        if(user != null) {
            return user.getLeaves();
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserDetailsImp(user);
    }
    
    public Iterable<Object> getNoStatusLeavesWithUSer() {
        return userRepository.findAllLeaves();
    }
}
