package model;

import java.util.Date;

public class Event {
	
	private int id;
	
	private int institutionId;
	
	private int showId;
	
	private Date eventDate;
	
	public Event(int InstitutionId, int ShowId, Date EventDate) {
		super();
		institutionId = InstitutionId;
		showId = ShowId;
		eventDate = EventDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	
	
	
}
