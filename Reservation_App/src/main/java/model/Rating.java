package model;

public class Rating {

	private int id;
	
	private int userId;
	
	private int rating;

	
	public Rating(int id, int userId, int rating) {
		super();
		this.id = id;
		this.userId = userId;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
