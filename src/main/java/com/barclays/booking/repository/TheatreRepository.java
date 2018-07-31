package com.barclays.booking.repository;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.barclays.booking.dto.RowAvailability;

@Repository
public class TheatreRepository {
	

	@Resource
	PartyBookingDataStore dataStore;
	
	public void saveTheatre(Map availabilityMap) {
		dataStore.saveAvailability(availabilityMap);
	}
	
	
	public Map<Integer, RowAvailability> getAvailabilityDetails() {
		return dataStore.getAvailabilityMap();
	}
	
}
