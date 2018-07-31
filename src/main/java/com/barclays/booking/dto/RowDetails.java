package com.barclays.booking.dto;

import java.util.List;

public class RowDetails {
	private int rowId;
	private List<SectionDetails> sectionDetails;
	
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public List<SectionDetails> getSectionDetails() {
		return sectionDetails;
	}
	public void setSectionDetails(List<SectionDetails> sectionDetails) {
		this.sectionDetails = sectionDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rowId;
		result = prime * result + ((sectionDetails == null) ? 0 : sectionDetails.hashCode());
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
		RowDetails other = (RowDetails) obj;
		if (rowId != other.rowId)
			return false;
		if (sectionDetails == null) {
			if (other.sectionDetails != null)
				return false;
		} else if (!sectionDetails.equals(other.sectionDetails))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RowDetails [rowId=" + rowId + ", sectionDetails=" + sectionDetails + "]";
	}

}
