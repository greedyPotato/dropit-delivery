package com.deliveryapi.dropitdeliveryapi.models;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="addresses")
@Component
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    @NotBlank(message = "street cannot be blank")
    private String street;
    @NotBlank(message = "line1 cannot be blank")
    private String line1;
    @NotBlank(message = "line2 cannot be blank")
    private String line2;
    @NotBlank(message = "country cannot be blank")
    private String country;
    @NotBlank(message = "postcode cannot be blank")
    private String postcode;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "timeslot_id")
    private Timeslot timeslot;

 
    
}
