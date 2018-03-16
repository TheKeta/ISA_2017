package model;
import java.util.HashMap;

public class Institution {
	
	private int Id;
	
	private String Name;
	
	private String Address;
	
	private HashMap<Integer, Integer> Ratings;

	
	public Institution(){
		Ratings = new HashMap<Integer, Integer>();
	}
	
	
	
	public Institution(String name, String address) {
		super();
		Name = name;
		Address = address;
		Ratings = new HashMap<Integer, Integer>();
	}

	public int getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public HashMap<Integer, Integer> getRatings() {
		return Ratings;
	}

	public void setRatings(HashMap<Integer, Integer> ratings) {
		Ratings = ratings;
	}
	
	
	
	
}
