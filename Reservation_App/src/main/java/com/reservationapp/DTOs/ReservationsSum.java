package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Reservation;

public class ReservationsSum {

	private List<Reservation> reservations;
	
	private double sum;

	
	
	public ReservationsSum(List<Reservation> reservations) {
		super();
		this.reservations = reservations;
		sum = 0;
		for(Reservation r : reservations){
			sum += r.getPrice();
		}
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
