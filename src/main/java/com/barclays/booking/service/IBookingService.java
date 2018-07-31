package com.barclays.booking.service;

import java.util.List;

import com.barclays.booking.dto.BookingHistoryDetails;
import com.barclays.booking.dto.BookingRequest;

public interface IBookingService {
	
	public BookingHistoryDetails processOrder(BookingRequest bookingDetails) throws Exception;

	public List<BookingHistoryDetails> retreiveOrders() throws Exception;

}
