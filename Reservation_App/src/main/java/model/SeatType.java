package model;

public class SeatType {
	private int Id;
	
	private String Name;

	
	public SeatType(String name) {
		super();
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
