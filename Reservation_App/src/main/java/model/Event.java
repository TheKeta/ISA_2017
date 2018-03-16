package model;

import java.util.Date;

public class Event {
	
	private int Id;
	
	private int InstitutionId;
	
	private int ShowId;
	
	private Date Date;
	
	public Event(int institutionId, int showId, java.util.Date date) {
		super();
		InstitutionId = institutionId;
		ShowId = showId;
		Date = date;
	}
	
	public int getId(){
		return Id;
	}

	public int getInstitutionId() {
		return InstitutionId;
	}

	public void setInstitutionId(int institutionId) {
		InstitutionId = institutionId;
	}

	public int getShowId() {
		return ShowId;
	}

	public void setShowId(int showId) {
		ShowId = showId;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}
	
	
	
}
