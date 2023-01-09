package com.deliveryapi.dropitdeliveryapi.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.deliveryapi.dropitdeliveryapi.dto.DeliveryRequest;
import com.deliveryapi.dropitdeliveryapi.models.Delivery;
import com.deliveryapi.dropitdeliveryapi.models.Timeslot;
import com.deliveryapi.dropitdeliveryapi.repository.DeliveryRepo;
import com.deliveryapi.dropitdeliveryapi.repository.TimeslotRepo;

import jakarta.validation.Valid;

@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    DeliveryRepo deliveryRepo;
    @Autowired
    TimeslotRepo timeslotRepo;

    @Override
    @Transactional()
    public Delivery bookDelivery( @Valid DeliveryRequest deliveryRequest) throws Exception {
      
        // error handling validtaions
        if(Delivery.amountOfBookingPerDay > 0){
            Delivery.amountOfBookingPerDay--;
            Optional<Timeslot> timeslot = timeslotRepo.findById(deliveryRequest.getTimeSlotId());
            Delivery delivery = new Delivery();
            delivery.setStatus("created");
            delivery.setTimeslot(timeslot.get());
            timeslot.get().setDelivery(delivery);
            return deliveryRepo.save(delivery);
        }else{
            throw new Exception("Cannot book more than 10 deliveries on a given day");
        }
        
    }

    @Override
    public Delivery completDelivery(int deliveryId) throws Exception {
        Optional<Delivery> delivery = deliveryRepo.findById(deliveryId);
        if(delivery.isPresent()){
        delivery.get().setStatus("complete");
        Delivery.amountOfBookingPerDay++;
        return deliveryRepo.save(delivery.get());
        }else{
            throw new Exception("Delivery Id is not exist, cant complete delivery");
        }
    }

    @Override
    @Transactional()
    public void cancelDelivery(Integer deliveryId) throws Exception {
        Optional<Delivery> deliveryById = deliveryRepo.findById(deliveryId);
        if(deliveryById.isPresent()){
            Delivery.amountOfBookingPerDay++;
            deliveryRepo.deleteById(deliveryId);
        }else{
            throw new Exception("Delivery Id is not exist, cant delete a delivery");
        }
     
    }

    @Override
    public List<Delivery> getDailyDeliveries() {
       List<Delivery> deliveries = deliveryRepo.findAll();

        // filter the deliveries to include only those that are scheduled for today
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startOfDay = today.with(LocalTime.MIN);
        LocalDateTime endOfDay = today.with(LocalTime.MAX);
        List<Delivery> Todaydeliveries = deliveries.stream()
        .filter(delivery -> LocalDateTime.parse(delivery.getTimeslot().getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")).isBefore(endOfDay)&&
        LocalDateTime.parse(delivery.getTimeslot().getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")).isAfter(startOfDay))
        .collect(Collectors.toList());
       
            
    return Todaydeliveries;
    }

    @Override
    public List<Delivery> getWeeklyDeliveries() {

        // filter the deliveries to include only those that are scheduled for this week
        List<Delivery> deliveries = deliveryRepo.findAll();
        LocalDateTime today= LocalDateTime.now();
        LocalDateTime firstDayOfTheWeek = today.with(DayOfWeek.MONDAY);
        LocalDateTime lastDayOfTheWeek = today.with(DayOfWeek.SUNDAY);

        List<Delivery> weeklyDeliveries = deliveries.stream()
        .filter(delivery -> {
            LocalDateTime deliveryDate = LocalDateTime.parse(delivery.getTimeslot().getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
          return deliveryDate.isAfter(firstDayOfTheWeek.minusDays(1)) && deliveryDate.isBefore(lastDayOfTheWeek.plusDays(1));
        })
        .collect(Collectors.toList());
        return weeklyDeliveries;
    }
    
}
