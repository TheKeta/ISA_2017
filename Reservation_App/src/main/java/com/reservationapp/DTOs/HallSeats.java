package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Hall;
import com.reservationapp.model.Seat;

public class HallSeats {

	private List<Hall> halls;
	private List<List<Seat>> seats;

	public HallSeats(List<List<Seat>> seats, List<Hall> halls) {
		super();
		this.halls = halls;
		this.seats = seats;		
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

	public List<List<Seat>> getSeats() {
		return seats;
	}

	public void setSeats(List<List<Seat>> seats) {
		this.seats = seats;
	}


}
