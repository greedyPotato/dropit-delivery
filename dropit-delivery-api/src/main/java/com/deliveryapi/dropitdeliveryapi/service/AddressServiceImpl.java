package com.deliveryapi.dropitdeliveryapi.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryapi.dropitdeliveryapi.integration.ResolveAddressConfig;
import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.deliveryapi.dropitdeliveryapi.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    Address address;

    @Autowired
    ResolveAddressConfig resolveAddressConfig;

    @Autowired
    AddressRepo addressRepo;

    @Override
    public Address resolveAddress(String searchTerm) throws IOException, InterruptedException {
        
        return ResolveAddressConfig.ResolveAddress(searchTerm);
       
    }

  

}