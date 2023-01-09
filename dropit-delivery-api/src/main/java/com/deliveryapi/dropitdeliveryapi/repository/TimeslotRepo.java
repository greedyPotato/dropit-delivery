package com.deliveryapi.dropitdeliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliveryapi.dropitdeliveryapi.models.Timeslot;

@Repository
public interface TimeslotRepo extends JpaRepository<Timeslot,Integer> {
    void deleteById(Integer id);
    
}
