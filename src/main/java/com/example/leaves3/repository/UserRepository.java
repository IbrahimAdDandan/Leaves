/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leaves3.repository;

import com.example.leaves3.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ibrahim
 */
public interface UserRepository extends CrudRepository<User, Long>{

    public User findByUsername(String username);
    
    @Query("select u, l from User u inner join u.leaves l where l.manipulated =false")
    List<Object> findAllLeaves();
    
    List<User> findAllByLeavesManipulatedIsFalse();
    
}
