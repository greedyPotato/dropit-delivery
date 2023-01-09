package com.deliveryapi.dropitdeliveryapi.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.deliveryapi.dropitdeliveryapi.models.Timeslot;

@Service
public interface TimeslotService {
    
     List<Timeslot> getTimeSlots(Address address);
}
