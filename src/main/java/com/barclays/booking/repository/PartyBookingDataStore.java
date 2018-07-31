package com.barclays.booking.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.barclays.booking.dto.BookingHistoryDetails;
import com.barclays.booking.dto.RowAvailability;

@Component
public class PartyBookingDataStore {
	
	private static Map<Integer, RowAvailability> availabilityMap = new LinkedHashMap<>();
	
	private static List<BookingHistoryDetails> history = new ArrayList<>();

	public static Map<Integer, RowAvailability> getAvailabilityMap() {
		return availabilityMap;
	}

	public static void setAvailabilityMap(Map<Integer, RowAvailability> availabilityMap) {
		PartyBookingDataStore.availabilityMap = availabilityMap;
	}
	
	
	public void updateAvailibity() {
		
	}
	
	public void saveAvailability(Map<Integer, RowAvailability> availabilityMap) {
		PartyBookingDataStore.availabilityMap = availabilityMap;
	}

	public static List<BookingHistoryDetails> getHistory() {
		return history;
	}

	public static void setHistory(List<BookingHistoryDetails> history) {
		PartyBookingDataStore.history = history;
	}
	
	public static void updateBookingHistory(BookingHistoryDetails orderDetails) {
		getHistory().add(orderDetails);
	}
	

}
