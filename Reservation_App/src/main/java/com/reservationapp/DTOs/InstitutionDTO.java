package com.reservationapp.DTOs;

import com.reservationapp.model.Institution;

public class InstitutionDTO {
	
	private String name = "";

	private String type = "";

	private String description = "";

	private String address = "";

	private Long id;
	
	public InstitutionDTO(Institution institution){
		this.name = institution.getName();
		this.type = institution.getType().getName();
		this.description = institution.getDescription();
		this.address = institution.getAddress();
		this.id = institution.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
