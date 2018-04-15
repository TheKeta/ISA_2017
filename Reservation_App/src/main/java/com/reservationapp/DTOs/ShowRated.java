package com.reservationapp.DTOs;

import com.reservationapp.model.Show;

public class ShowRated {

	private Show show; 
	
	private double rating;

	public ShowRated(){
		
	}
	
	public ShowRated(Show show, double rating) {
		super();
		this.show = show;
		this.rating = rating;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
}
