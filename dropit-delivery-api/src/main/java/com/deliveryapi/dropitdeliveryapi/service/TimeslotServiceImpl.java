package com.deliveryapi.dropitdeliveryapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.deliveryapi.dropitdeliveryapi.models.Timeslot;
import com.deliveryapi.dropitdeliveryapi.repository.TimeslotRepo;

@Service
public class TimeslotServiceImpl implements TimeslotService{

    @Autowired
    TimeslotRepo timeslotRepo;
    
    
    @Override
    public List<Timeslot> getTimeSlots(Address address){
        
        List<Timeslot> allTimeSlots = timeslotRepo.findAll();
        List<Timeslot> filteredTimeslots = allTimeSlots.stream()
        .filter(timeslot -> timeslot.getAddresses().stream()
            .anyMatch(ad -> ad.getPostcode().equals(address.getPostcode()) && ad.getCountry().equals(address.getCountry())))
        .collect(Collectors.toList());
        return filteredTimeslots;
    }
    
}
