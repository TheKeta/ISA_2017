package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Event;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;

public class EventReservationsSeats {

	private Event event;
	
	private List<Seat> seats;
	
	private List<Reservation> reservations;
	

	public EventReservationsSeats(Event event, List<Seat> seats, List<Reservation> reservations) {
		super();
		this.event = event;
		this.seats = seats;
		this.reservations = reservations;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
