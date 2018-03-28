package model;

import java.util.HashMap;

public class Show {
	private int id;
	
	private String name;
	
	private int ratingId;

	private int genreId;
	
	private int length;

	public Show(int id, String name, int ratingId, int genreId, int length) {
		super();
		this.id = id;
		this.name = name;
		this.ratingId = ratingId;
		this.genreId = genreId;
		this.length = length;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
