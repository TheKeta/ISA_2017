package com.reservationapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Friendship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = true)
	private User sender;
	
	@ManyToOne(optional = true)
	private User reciever;
	
	@Column(nullable = false)
	private int accepted;



	public Friendship(User sender, User reciever, int accepted) {
		super();
		this.sender = sender;
		this.reciever = reciever;
		this.accepted = accepted;
	}
	
	public Friendship(){
		
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
}
