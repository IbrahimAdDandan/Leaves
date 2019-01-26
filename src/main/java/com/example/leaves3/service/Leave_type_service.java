/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.service;

import com.example.leaves3.domain.LeaveType;
import com.example.leaves3.repository.LeaveTypeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ibrahim
 */

@Service
public class Leave_type_service {
    
    @Autowired
    private LeaveTypeRepository ltp;

    public Iterable<LeaveType> getLeave_type() {
        return  ltp.findAll();
    }
    
    public LeaveType get() {
        return new LeaveType();
    }
    
    public Optional<LeaveType> getLeave(Long id) {
        return ltp.findById(id);
    }
    
    public void remove(Long id) {
        ltp.deleteById(id);
    }

    public LeaveType add(LeaveType leave_t) {
        return ltp.save(leave_t);
    }
    
}
