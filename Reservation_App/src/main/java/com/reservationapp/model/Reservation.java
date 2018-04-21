package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Reservation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Column(nullable = false)
	private double price;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Seat seat;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Event event;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private User user;
	
	@Column(nullable = false)
	private boolean quick;

	public Reservation(){
		
	}

	public Reservation(double price, Seat seat, Event event, User user, boolean quick) {
		super();
		this.price = price;
		this.seat = seat;
		this.event = event;
		this.user = user;
		this.quick = quick;
	}

	
	public boolean isQuick() {
		return quick;
	}

	public void setQuick(boolean quick) {
		this.quick = quick;
	}

	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Seat getSeats() {
		return seat;
	}

	public void setSeats(Seat seat) {
		this.seat = seat;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
		
}
