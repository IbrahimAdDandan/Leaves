/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.service;

import com.example.leaves3.domain.Leaves;
import com.example.leaves3.repository.LeaveRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ibrahim
 */

@Service
public class LeaveService {
    
    @Autowired
    private LeaveRepository leaveRepository;
    
    public Iterable<Leaves> getLeave_type() {
        return  leaveRepository.findAll();
    }
    
    public Leaves get() {
        return new Leaves();
    }
    
    public Optional<Leaves> getLeave(Long id) {
        return leaveRepository.findById(id);
    }
    
    public void remove(Long id) {
        leaveRepository.deleteById(id);
    }

    public Leaves add(Leaves leave) {
        return leaveRepository.save(leave);
    }
    
    public Iterable<Leaves> getAprovedLeaves() {
        return leaveRepository.findAllByApproval(true);
    }
    
    public Iterable<Leaves> getRejectedLeaves() {
        return leaveRepository.findAllByApproval(false);
    }
    
    public Iterable<Leaves> getNoStatusLeaves() {
        return leaveRepository.findAllByManipulatedIsFalse();
    }
    
    
    public int manipulate(Long id, boolean status) {
        return leaveRepository.updateAllLeavesSetApprovalForId(id, status);
    }
    
    public Iterable<Leaves> saveAll(List<Leaves> leaves) {
        return leaveRepository.saveAll(leaves);
    }
}
