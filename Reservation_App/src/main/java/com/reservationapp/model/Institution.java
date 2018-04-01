package com.reservationapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Institution implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	 
	@Column(nullable = false)
	private String address;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "institution")
	private Set<InstitutionRating> rating;

	public Institution(){
		
	}
	
	public Institution(String name, String address, Set<InstitutionRating> rating) {
		super();
		this.name = name;
		this.address = address;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<InstitutionRating> getRating() {
		return rating;
	}

	public void setRating(Set<InstitutionRating> rating) {
		this.rating = rating;
	}

	
	
	
}
