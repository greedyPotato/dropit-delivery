package com.deliveryapi.dropitdeliveryapi.dto;

import org.springframework.stereotype.Component;

import com.deliveryapi.dropitdeliveryapi.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class DeliveryRequest {

    private User user;
    @NotNull(message = "time slot id cannot be blank")
    private int timeSlotId;
}
