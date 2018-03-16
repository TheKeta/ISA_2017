package model;

import java.util.HashMap;

public class Show {
	private int Id;
	
	private String Name;
	
	private HashMap<Integer, Integer> Ratings;

	private int GenreId;
	
	private int Length;
	
	
	public Show(){
		Ratings = new HashMap<Integer, Integer>();
	}

	public Show(String name, int genreId, int length) {
		Name = name;
		GenreId = genreId;
		Length = length;
		Ratings = new HashMap<Integer, Integer>();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public HashMap<Integer, Integer> getRatings() {
		return Ratings;
	}

	public void setRatings(HashMap<Integer, Integer> ratings) {
		Ratings = ratings;
	}
	
	public int getGenre() {
		return GenreId;
	}

	public void setGenre(int genreId) {
		GenreId = genreId;
	}


	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}

	
	
}
