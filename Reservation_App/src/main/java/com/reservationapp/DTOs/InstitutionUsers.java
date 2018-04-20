package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.Institution;
import com.reservationapp.model.User;

public class InstitutionUsers {

	Institution institution;
	
	List<User> users;

	public InstitutionUsers(Institution institution, List<User> users) {
		super();
		this.institution = institution;
		this.users = users;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
