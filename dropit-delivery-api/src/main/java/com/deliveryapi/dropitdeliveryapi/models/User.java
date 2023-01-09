package com.deliveryapi.dropitdeliveryapi.models;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class User {
   
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "email cannot be blank")
    @Email
    private String email;

}
