package com.barclays.booking.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.barclays.booking.dto.BookingHistoryDetails;
import com.barclays.booking.dto.BookingRequest;
import com.barclays.booking.dto.RowAvailability;
import com.barclays.booking.dto.RowDetails;
import com.barclays.booking.dto.SectionDetails;
import com.barclays.booking.dto.TheatreRequest;
import com.barclays.booking.repository.PartyBookingDataStore;

@Component
public class PartyBookingUtils {

	@Resource
	PartyBookingDataStore dataStore;

	public Map<Integer,RowAvailability> populateAvailabilityDetails(TheatreRequest theatreRequest) {

		Map<Integer, RowAvailability> layoutMap = new HashMap<>();
		RowAvailability rowInfo = null;

		if(theatreRequest != null && !(CollectionUtils.isEmpty(theatreRequest.getRowDetails()))) {
			for(RowDetails rowDetails : theatreRequest.getRowDetails()) {
				rowInfo = new RowAvailability();
				rowInfo.setSeatsAvailable(Boolean.TRUE);
				for(SectionDetails section : rowDetails.getSectionDetails()) {
					rowInfo.setNoOfSeatsAvailable(section.getNoofSeats()+rowInfo.getNoOfSeatsAvailable());
					section.setSeatsAvailable(Boolean.TRUE);
					section.setTotalAvailable(section.getNoofSeats()+section.getTotalAvailable());
				}
				rowInfo.setRowDetails(rowDetails);
				layoutMap.put(rowDetails.getRowId(), rowInfo);
			}
		}

		return layoutMap;

	}

	public BookingHistoryDetails processBooking(BookingRequest bookingDetails) throws Exception {

		String bookingStatus = null;
		BookingHistoryDetails historyDetails = new BookingHistoryDetails();
		historyDetails.setNoOfTickets(bookingDetails.getNoOfSeats());
		historyDetails.setName(bookingDetails.getName());
		int requestedSeats = bookingDetails.getNoOfSeats();
		int totalSeatsAvailableInTheatre = 0;

		Map<Integer,RowAvailability> availabilityMap = PartyBookingDataStore.getAvailabilityMap();

		if(CollectionUtils.isEmpty(availabilityMap)) {
			throw new Exception("Theatre Layout is not available");
		}
		for (Map.Entry<Integer,RowAvailability> availableMap : availabilityMap.entrySet()) {
			Integer rowNumber =  availableMap.getKey();
			RowAvailability rowInfo = availableMap.getValue();
			totalSeatsAvailableInTheatre = totalSeatsAvailableInTheatre+ rowInfo.getNoOfSeatsAvailable();
			if(rowInfo.isSeatsAvailable() && rowInfo.getNoOfSeatsAvailable() >= requestedSeats) {
				RowDetails rowDetail =rowInfo.getRowDetails();
				List<SectionDetails> sectionDetails =rowDetail.getSectionDetails();
				for(SectionDetails section : sectionDetails) {

					if(section.isSeatsAvailable() && section.getTotalAvailable() >= requestedSeats) {
						rowInfo.setNoOfSeatsAvailable(rowInfo.getNoOfSeatsAvailable() - requestedSeats);
						section.setTotalAvailable(section.getTotalAvailable()- requestedSeats);
						section.setSeatsAvailable(section.getTotalAvailable() <=0?Boolean.FALSE:Boolean.TRUE);
						availabilityMap.put(rowNumber, rowInfo);
						dataStore.saveAvailability(availabilityMap);
						historyDetails.setRowNumber(rowDetail.getRowId());
						historyDetails.setSectionNumber(section.getSectionId());
						bookingStatus = ApplicationConstants.STATUS_CONFIRMED;
						historyDetails.setBookingStatus(bookingStatus);
						PartyBookingDataStore.updateBookingHistory(historyDetails);
						return historyDetails;


					}else {
						bookingStatus = ApplicationConstants.CALL_T0_PARTY;
					}
				}
			}


			if(rowInfo.isSeatsAvailable() && rowInfo.getNoOfSeatsAvailable() < requestedSeats) {
				bookingStatus = ApplicationConstants.CALL_T0_PARTY;
			}

			if(totalSeatsAvailableInTheatre < requestedSeats) {
				bookingStatus = ApplicationConstants.NO_SEATS_AVAILABLE;
			}

		}

		historyDetails.setBookingStatus(bookingStatus);
		PartyBookingDataStore.updateBookingHistory(historyDetails);
		return historyDetails;
	}

}
