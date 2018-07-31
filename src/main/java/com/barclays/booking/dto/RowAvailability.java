package com.barclays.booking.dto;

public class RowAvailability {
	
	
	private boolean seatsAvailable;
	
	private RowDetails rowDetails;
	
	private int noOfSeatsAvailable;

	public boolean isSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(boolean seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public RowDetails getRowDetails() {
		return rowDetails;
	}

	public void setRowDetails(RowDetails rowDetails) {
		this.rowDetails = rowDetails;
	}

	public int getNoOfSeatsAvailable() {
		return noOfSeatsAvailable;
	}

	public void setNoOfSeatsAvailable(int noOfSeatsAvailable) {
		this.noOfSeatsAvailable = noOfSeatsAvailable;
	}
	

}
