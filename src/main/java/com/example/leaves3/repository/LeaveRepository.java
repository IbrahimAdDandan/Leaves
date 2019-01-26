/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.repository;

import com.example.leaves3.domain.Leaves;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ibrahim
 */
public interface LeaveRepository extends CrudRepository<Leaves, Long>{
    
    List<Leaves> findAllByApproval(boolean approval);
    
    List<Leaves> findAllByApprovalIsNull();
    
    List<Leaves> findAllByManipulatedIsFalse();
    
    List<Leaves> findAllByManipulated(boolean manipulated);
    
    List<Leaves> findAllByApprovalAndManipulated(boolean approval, boolean manipulated);
    
    List<Leaves> findAllByUser(Long id);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE Leaves SET approval =:status, manipulated =1 WHERE id =:id")
    int updateAllLeavesSetApprovalForId(@Param("id") Long id, @Param("status") boolean status);
    
    
}
