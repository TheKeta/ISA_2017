package com.reservationapp.model;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {
	@NotEmpty
	private String email = "";

	@NotEmpty
	private String firstName = "";

	@NotEmpty
	private String lastName = "";

	@NotEmpty
	private String city = "";
	
	@NotEmpty
	private String oldPassword = "";
	

	@NotEmpty
	private String password = "";

	@NotEmpty
	private String repeatedPassword = "";

	public UserForm(){
		
	}
	
	public UserForm(User user) {
		// TODO Auto-generated constructor stub
		this.email = user.getEmail();
		this.city = user.getCity();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
}
