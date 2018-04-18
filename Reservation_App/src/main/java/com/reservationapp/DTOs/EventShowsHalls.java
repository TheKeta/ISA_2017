package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Show;

public class EventShowsHalls {

	private Event event;
	
	private List<Show> shows;
	
	private List<Hall> halls;

	public EventShowsHalls(Event event, List<Show> shows, List<Hall> halls) {
		super();
		this.event = event;
		this.shows = shows;
		this.halls = halls;
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

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}
	
	
}
