package com.reservationapp.DTOs;

import com.reservationapp.model.Reservation;

public class ReservationDTO {

	private Long id;
	
	private Long eventDate;
	
	private int seat;
	
	private String user;
	
	private boolean quick;
	
	private double price;
	
	public ReservationDTO(Reservation res){
		this.id = res.getId();
		this.eventDate = res.getEvent().getEventDate().getTime();
		this.seat = res.getSeats().getSeatNumber();
		this.user = res.getUser().getEmail();
		this.quick = res.isQuick();
		this.price = res.getPrice();
	}

	public ReservationDTO(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEventDate() {
		return eventDate;
	}

	public void setEventDate(Long eventDate) {
		this.eventDate = eventDate;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isQuick() {
		return quick;
	}

	public void setQuick(boolean quick) {
		this.quick = quick;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
