package com.barclays.booking.dto;

public class SectionDetails {
	private int sectionId;
	private int noofSeats;
	private boolean seatsAvailable;
	private int totalAvailable;
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getNoofSeats() {
		return noofSeats;
	}
	public void setNoofSeats(int noofSeats) {
		this.noofSeats = noofSeats;
	}
	public boolean isSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(boolean seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public int getTotalAvailable() {
		return totalAvailable;
	}
	public void setTotalAvailable(int totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noofSeats;
		result = prime * result + sectionId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectionDetails other = (SectionDetails) obj;
		if (noofSeats != other.noofSeats)
			return false;
		if (sectionId != other.sectionId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SectionDetails [sectionId=" + sectionId + ", noofSeats=" + noofSeats + "]";
	}
	
}
