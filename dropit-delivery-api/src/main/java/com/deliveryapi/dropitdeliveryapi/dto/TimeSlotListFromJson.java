package com.deliveryapi.dropitdeliveryapi.dto;

import java.util.List;

import com.deliveryapi.dropitdeliveryapi.models.Timeslot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotListFromJson {
    private List<Timeslot> ListTimeSlot;
    
}
