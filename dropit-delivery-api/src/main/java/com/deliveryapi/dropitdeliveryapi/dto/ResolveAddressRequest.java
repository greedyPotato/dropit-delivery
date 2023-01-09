package com.deliveryapi.dropitdeliveryapi.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ResolveAddressRequest {
    @NotBlank(message = "scearch term cannot be blank")
    private String searchTerm;
}
