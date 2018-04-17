package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Event;
import com.reservationapp.model.Show;

public class EventShows {

	private Event event;
	
	private List<Show> shows;

	public EventShows(Event event, List<Show> shows) {
		super();
		this.event = event;
		this.shows = shows;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	
	
}
