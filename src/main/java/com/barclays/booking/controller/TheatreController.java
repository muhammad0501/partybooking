package com.barclays.booking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.booking.dto.RowAvailability;
import com.barclays.booking.dto.TheatreRequest;
import com.barclays.booking.service.impl.TheatreService;

@RestController
@RequestMapping(value ="/theatre", consumes = "application/json")
public class TheatreController {

	@Autowired
	TheatreService theatreService;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createTheatre(@RequestBody TheatreRequest theatreRequest){
		ResponseEntity<Object> response = null;

		try {
			theatreService.createTheatre(theatreRequest);
			response = new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getTheatreDetails(){
		ResponseEntity<Object> response = null;
        Map<Integer, RowAvailability> availabilityDetails = null;
		try {
			availabilityDetails = theatreService.getTheatreDetails();
			response = new ResponseEntity<>(availabilityDetails,HttpStatus.OK);
		}catch(Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
