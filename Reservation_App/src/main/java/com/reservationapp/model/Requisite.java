package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Requisite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String creator; //user's ID
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = true)
	private byte[] picture;
	
	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private String type; // new or used

	public Requisite(Long id, String creator, String name, byte[] picture, String description, int price, String type) {
		super();
		this.id = id;
		this.creator = creator;
		this.name = name;
		this.picture = picture;
		this.description = description;
		this.price = price;
		this.type = type;
	}
	
	public Requisite() {}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
