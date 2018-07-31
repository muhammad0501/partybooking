package com.barclays.booking.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.barclays.booking.dto.RowAvailability;
import com.barclays.booking.dto.TheatreRequest;
import com.barclays.booking.repository.TheatreRepository;
import com.barclays.booking.service.ITheatreService;
import com.barclays.booking.utils.PartyBookingUtils;

@Service
public class TheatreService implements ITheatreService {
	

	@Resource
	TheatreRepository theatreRepository;
	
	@Resource
	PartyBookingUtils utils;

	@Override
	public void createTheatre(TheatreRequest theatreRequest) throws Exception{
		
		Map<Integer, RowAvailability> availabilityMap = utils.populateAvailabilityDetails(theatreRequest);
		theatreRepository.saveTheatre(availabilityMap);
		
		
	}

	public Map<Integer, RowAvailability> getTheatreDetails() throws Exception{
		return theatreRepository.getAvailabilityDetails();
		
	}




}
