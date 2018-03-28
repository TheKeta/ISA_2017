package model;

public class InstitutionRating {

	private int id;
	
	private int userId;
	
	private int rating;
 
	private int intitutionId;

	public InstitutionRating(int id, int userId, int rating, int intitutionId) {
		super();
		this.id = id;
		this.userId = userId;
		this.rating = rating;
		this.intitutionId = intitutionId;
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

	public int getIntitutionId() {
		return intitutionId;
	}

	public void setIntitutionId(int intitutionId) {
		this.intitutionId = intitutionId;
	}
	
	
}
