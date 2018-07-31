package com.barclays.booking.service;

import com.barclays.booking.dto.TheatreRequest;

public interface ITheatreService {
	
	public void createTheatre(TheatreRequest theatreRequest) throws Exception;

}
