package com.deliveryapi.dropitdeliveryapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryapi.dropitdeliveryapi.dto.ResolveAddressRequest;
import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.deliveryapi.dropitdeliveryapi.service.AddressService;

import jakarta.validation.Valid;

@RestController
public class AddressController {
    
    @Autowired
    AddressService addressService;
   

  @PostMapping("/resolve-address")
  public Address resolveAddress(@Valid @RequestBody ResolveAddressRequest searchTerm) throws IOException, InterruptedException {
    
    // resolve the single line address into a structured Address object using an external geocoding API
    return addressService.resolveAddress(searchTerm.getSearchTerm());
  }
}
