package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(nullable = false)
	private Long senderID;
	
	@Column(nullable = false)
	private Long reciverID;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private boolean read;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getSenderID() {
		return senderID;
	}

	public void setSenderID(Long senderID) {
		this.senderID = senderID;
	}

	public Long getReciverID() {
		return reciverID;
	}

	public void setReciverID(Long reciverID) {
		this.reciverID = reciverID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Message(Long id, Long version, Long senderID, Long reciverID, String text, boolean read) {
		super();
		this.id = id;
		this.version = version;
		this.senderID = senderID;
		this.reciverID = reciverID;
		this.text = text;
		this.read = read;
	}
	
	public Message() {}
	

}
