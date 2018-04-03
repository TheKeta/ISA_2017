package com.reservationapp.DTOs;

import com.reservationapp.model.Institution;

public class InstitutionRated {

	private Institution institution; 
	
	private double rating;

	public InstitutionRated(){
	
	}
	
	public InstitutionRated(Institution institution, double rating) {
		super();
		this.institution = institution;
		this.rating = rating;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}
