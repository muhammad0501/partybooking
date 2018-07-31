package com.barclays.booking.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.barclays.booking.dto.BookingHistoryDetails;
import com.barclays.booking.dto.BookingRequest;
import com.barclays.booking.repository.PartyBookingDataStore;
import com.barclays.booking.service.IBookingService;
import com.barclays.booking.utils.PartyBookingUtils;

@Service
public class BookingService implements IBookingService{
	
	
	@Resource
	PartyBookingUtils utils;
	
	@Resource
	PartyBookingDataStore dataStore;

	@Override
	public BookingHistoryDetails processOrder(BookingRequest bookingDetails) throws Exception {
		
		return utils.processBooking(bookingDetails);
		
		
	}

	@Override
	public List<BookingHistoryDetails> retreiveOrders() throws Exception {
		
		return PartyBookingDataStore.getHistory();
	}

}
