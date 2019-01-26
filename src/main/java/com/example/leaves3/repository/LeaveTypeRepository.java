/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.repository;

import com.example.leaves3.domain.LeaveType;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ibrahim
 */
public interface LeaveTypeRepository extends CrudRepository<LeaveType, Long>{
    
}
