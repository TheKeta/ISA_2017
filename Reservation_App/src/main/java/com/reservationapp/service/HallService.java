package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;

public interface HallService {

	Hall findOne(Long id);

	List<Hall> findAll();
	
	Hall save(Hall hall);
	
	List<Hall> save(List<Hall> hall);
	
	Hall delete(Long id);
	
	
	void delete(List<Long> ids);
	
	List<Hall> findByInstitution(Institution institution);
}
