package com.deliveryapi.dropitdeliveryapi;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.deliveryapi.dropitdeliveryapi.models.Delivery;
import com.deliveryapi.dropitdeliveryapi.models.Timeslot;
import com.deliveryapi.dropitdeliveryapi.repository.TimeslotRepo;

@SpringBootTest
class DropitDeliveryApiApplicationTests {

	@Autowired
	TimeslotRepo timeslotRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateTimeSlot(){
		Timeslot ts = new Timeslot();
		ts.setStartTime("2022-12-03T10:15:30");
		ts.setEndTime("2022-12-03T10:15:30");

		Delivery d = new Delivery();
		d.setStatus("start");
		
		ts.setDelivery(d);
		timeslotRepo.save(ts);

		
	}

}
