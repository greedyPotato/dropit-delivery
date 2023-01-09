package com.deliveryapi.dropitdeliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliveryapi.dropitdeliveryapi.models.Delivery;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery,Integer>{
    
}
