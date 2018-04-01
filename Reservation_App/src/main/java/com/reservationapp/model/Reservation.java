package com.reservationapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private double price;
	
	@OneToMany()
	private Set<Seat> seats;
	
	@ManyToOne(optional = false)
	private Event event;
	
	@ManyToOne(optional = false)
	private User user;

	public Reservation(){
		
	}
	
	public Reservation(double price, Set<Seat> seats, Event event, User user) {
		super();
		this.price = price;
		this.seats = seats;
		this.event = event;
		this.user = user;
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

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
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
