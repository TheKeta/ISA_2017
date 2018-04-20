package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;

public class ReservedSeats {

	List<Reservation> reservations;
	
	List<Seat> seats;

	public ReservedSeats(List<Reservation> reservations, List<Seat> seats) {
		super();
		this.reservations = reservations;
		this.seats = seats;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
}
