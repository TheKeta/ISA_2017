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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 
	@ManyToOne(optional = false)
	private Institution institution;
	
	@ManyToOne(optional = false)
	private Show show;
	
	@Column(nullable = false)
	private Date eventDate;
	
	public Event(){
		
	}
	
	public Event(Institution institution, Show show, Date eventDate) {
		super();
		Assert.notNull(institution, "Institution can not be null");
		Assert.notNull(show, "Show can not be null");
		this.institution = institution;
		this.show = show;
		this.eventDate = eventDate;
	}

	public Long getId() {
		return id;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
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
