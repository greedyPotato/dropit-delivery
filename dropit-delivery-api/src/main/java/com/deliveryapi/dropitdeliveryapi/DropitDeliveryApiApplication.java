package com.deliveryapi.dropitdeliveryapi;

import com.deliveryapi.dropitdeliveryapi.models.Address;
import com.deliveryapi.dropitdeliveryapi.models.Timeslot;
import com.deliveryapi.dropitdeliveryapi.repository.AddressRepo;
import com.deliveryapi.dropitdeliveryapi.repository.TimeslotRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication

public class DropitDeliveryApiApplication implements ApplicationRunner{
	
	@Autowired
	TimeslotRepo timeslotRepo;
	@Autowired
	AddressRepo addressRepo;
	public static void main(String[] args) throws IOException {
		SpringApplication.run(DropitDeliveryApiApplication.class, args);       
	}


	//parsing json file of timeslots to the DB:
	@Override
	public void run(ApplicationArguments args) throws Exception {
		String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/static/Courier.json")));

		Gson gson = new Gson();
	JsonObject json = gson.fromJson(jsonString, JsonObject.class);
	JsonArray timeslotsArray = json.getAsJsonArray("timeslots");

	ObjectMapper mapper = new ObjectMapper();
	List<Timeslot> timeslots = mapper.readValue(timeslotsArray.toString(), new TypeReference<List<Timeslot>>(){});
		for(Timeslot ts : timeslots){
			for (Address address : ts.getAddresses()) {
				address.setTimeslot(ts);
			}
			timeslotRepo.save(ts);		
		}
		
	}
}
