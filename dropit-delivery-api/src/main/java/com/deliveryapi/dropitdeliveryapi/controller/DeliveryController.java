package com.deliveryapi.dropitdeliveryapi.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deliveryapi.dropitdeliveryapi.dto.DeliveryRequest;
import com.deliveryapi.dropitdeliveryapi.models.Delivery;
import com.deliveryapi.dropitdeliveryapi.service.DeliveryService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping()
    public Delivery bookDelivery(@Valid @RequestBody DeliveryRequest DeliveryRequest) throws Exception {
        // book a delivery for the given user and timeslot
        return deliveryService.bookDelivery(DeliveryRequest);
    }

    @PostMapping("/{deliveryId}/complete")
    public Delivery completeDelivery(@PathVariable int deliveryId) throws Exception {
        // mark the delivery with the given ID as completed
        return deliveryService.completDelivery(deliveryId);
    }

    @CacheEvict("delivery-cache")
    @DeleteMapping("/{deliveryId}")
    public void cancelDelivery(@PathVariable Integer deliveryId) throws Exception {
        // cancel the delivery with the given ID
        deliveryService.cancelDelivery(deliveryId);
    
    }

    
    @GetMapping("/daily")
    public List<Delivery> getTodayDeliveries() {
        return deliveryService.getDailyDeliveries();
        // retrieve all deliveries for today
    }

    @GetMapping("/weekly")
    public List<Delivery> getWeeklyDeliveries() {
        // retrieve all deliveries for the current week
        return null;
  }
}