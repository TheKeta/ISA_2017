package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Genre;
import com.reservationapp.model.Show;
import com.reservationapp.model.ShowType;

public class ShowGenresTypes {

	private Show show;
	
	private List<Genre> genres;
	
	private List<ShowType> types;

	public ShowGenresTypes(Show show, List<Genre> genres, List<ShowType> types) {
		super();
		this.show = show;
		this.genres = genres;
		this.types = types;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<ShowType> getTypes() {
		return types;
	}

	public void setTypes(List<ShowType> types) {
		this.types = types;
	}
	
	
	
}
