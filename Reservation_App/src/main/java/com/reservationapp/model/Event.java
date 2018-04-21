package com.reservationapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;


@Entity
public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private Hall hall;
	
	@ManyToOne(optional = false)
	private Show show;
	
	@Column(nullable = false)
	private Date eventDate;
	
	@Column(nullable = false)
	private double price;
	
	public Event(){
		
	}
	
	public Event(Hall hall, Show show, Date eventDate, double price) {
		super();
		Assert.notNull(hall, "Hall can not be null");
		Assert.notNull(show, "Show can not be null");
		this.hall = hall;
		this.show = show;
		this.eventDate = eventDate;
		this.price = price;
	}

	
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
}
