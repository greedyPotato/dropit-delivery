package com.deliveryapi.dropitdeliveryapi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.deliveryapi.dropitdeliveryapi.dto.DeliveryRequest;
import com.deliveryapi.dropitdeliveryapi.models.Delivery;

@Service
public interface DeliveryService {
    
    Delivery bookDelivery(DeliveryRequest deliveryrRequest) throws Exception;
    Delivery completDelivery(int deliveryId) throws Exception;
    void cancelDelivery(Integer deliveryId) throws Exception;
    List<Delivery> getDailyDeliveries();
    List<Delivery> getWeeklyDeliveries();
}
