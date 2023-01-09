package com.deliveryapi.dropitdeliveryapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.deliveryapi.dropitdeliveryapi.models.Timeslot;
import com.deliveryapi.dropitdeliveryapi.service.TimeslotService;

import jakarta.validation.Valid;



@RestController
public class TimeslotController {
    
    @Autowired
    TimeslotService timeslotService;
    
    @PostMapping("/timeslots")
    public List<Timeslot> getTimeslots(@Valid @RequestBody Address address)  {
       return timeslotService.getTimeSlots(address);
       
        
    }
}
