package com.barclays.booking.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.barclays.booking.dto.BookingHistoryDetails;
import com.barclays.booking.dto.BookingRequest;
import com.barclays.booking.service.IBookingService;

@RestController
@RequestMapping(value= "/order", produces = "application/json")
public class BookingController {

    Logger logger = LoggerFactory.getLogger(BookingController.class);
    
		@Autowired
		IBookingService bookingService;


		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Object> createOrder(@RequestBody BookingRequest orderRequest){

			logger.debug("Request Details for the CreateOrder is {}",orderRequest);
		    ResponseEntity<Object> response = null;
		    BookingHistoryDetails orderDetails = null;

			try {
				orderDetails = bookingService.processOrder(orderRequest);
				response = new ResponseEntity<>(orderDetails,HttpStatus.CREATED);
				logger.debug("Order Created Successfully");
			} catch (Exception e) {
				logger.error("Problem Occured while creating an order {}",e);
				response = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		}
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<Object> getOrderDetails(){

		    ResponseEntity<Object> response = null;
		    List<BookingHistoryDetails> history = null;

			try {
				history = bookingService.retreiveOrders();
				response = new ResponseEntity<>(history,HttpStatus.OK);
				logger.debug("Order Created Successfully");
			} catch (Exception e) {
				logger.error("Problem Occured while creating an order {}",e);
				response = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

			}
			return response;
		}

		

	}



