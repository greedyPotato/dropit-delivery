package com.deliveryapi.dropitdeliveryapi.integration;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;


@Component
public class ResolveAddressConfig{

    @Autowired
    static
    Address address;
    
    public static Address ResolveAddress(String searchTerm) throws IOException, InterruptedException{
      
        String url = "https://api.geoapify.com/v1/geocode/autocomplete?text="+searchTerm+"&apiKey=79d34ad2ee034434ae9e3ed3f127165a";
		WebClient.Builder builder = WebClient.builder();
		String response = builder.build().get().uri(url).retrieve().bodyToMono(String.class).block();
		

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>(){});
		List<Map<String, Object>> features = (List<Map<String, Object>>) jsonMap.get("features");
		Map<String, Object> properties = (Map<String, Object>) features.get(0).get("properties");

		 
		address.setStreet((String) properties.get("street"));
		address.setLine1((String) properties.get("address_line1"));
		address.setLine2((String) properties.get("address_line2"));
		address.setCountry((String) properties.get("country"));
		address.setPostcode((String) properties.get("postcode"));
        return address;
    }

}