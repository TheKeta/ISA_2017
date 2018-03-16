package model;

public class User {

	private int Id;
	
	private String Email;
	
	private String Username;
	
	private String Password;
	
	private String FirstName;
	
	private String LastName;
	
	private int UserTypeId;

	
	public User(){
		
	}
	
	public User(String email, String username, String password, String firstName, String lastName, int userTypeId){
		Email = email;
		Username = username;
		Password = password;
		FirstName = firstName;
		LastName = lastName;
		UserTypeId = userTypeId;
	}
	
	
	
	public int getId() {
		return Id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getUserTypeId() {
		return UserTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		UserTypeId = userTypeId;
	}
	
	
	
	
}
