package com.reservationapp.DTOs;

import java.util.List;

import com.reservationapp.model.InstitutionType;
import com.reservationapp.model.User;

public class InstitutionTypeUsers {

	InstitutionType institutionType;
	
	List<User> users;

	public InstitutionTypeUsers(InstitutionType institutionType, List<User> users) {
		super();
		this.institutionType = institutionType;
		this.users = users;
	}

	public InstitutionType getInstitution() {
		return institutionType;
	}

	public void setInstitution(InstitutionType institutionType) {
		this.institutionType = institutionType;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
}
