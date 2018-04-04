package com.reservationapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Requisite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = true)
	private String picture;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToOne(optional = false)
	private RequisiteType type; // new or used

	public Requisite(String picture, String description, RequisiteType type) {
		super();
		this.picture = picture;
		this.description = description;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RequisiteType getType() {
		return type;
	}

	public void setType(RequisiteType type) {
		this.type = type;
	}
	
	

}
