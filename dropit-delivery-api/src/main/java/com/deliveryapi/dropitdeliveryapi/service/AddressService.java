package com.deliveryapi.dropitdeliveryapi.service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import com.deliveryapi.dropitdeliveryapi.models.Address;

@Service
public interface AddressService {
    
    public Address resolveAddress(String searchTerm) throws IOException, InterruptedException;
  
}
