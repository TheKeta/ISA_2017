package model;

public class ShowRating {

	private int id;
	
	private int userId;
	
	private int rating;
 
	private int showId;

	public ShowRating(int id, int userId, int rating, int showId) {
		super();
		this.id = id;
		this.userId = userId;
		this.rating = rating;
		this.showId = showId;
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

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}
	
	
}
