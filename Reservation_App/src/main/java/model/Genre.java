package model;

public class Genre {

	private int Id;
	
	private String Name;
	
	

	public Genre(String name) {
		Name = name;
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
	
	
	
}
