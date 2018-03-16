package model;

public class UserType {

	private int Id;
	
	private int Name;

	
	
	public UserType(int name) {
		super();
		Name = name;
	}

	public int getId() {
		return Id;
	}

	public int getName() {
		return Name;
	}

	public void setName(int name) {
		Name = name;
	}
	
	
}
