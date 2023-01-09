package com.deliveryapi.dropitdeliveryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliveryapi.dropitdeliveryapi.models.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Integer>{
    
}
