package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Institution;

public interface InstitutionService {

	Institution findOne(Long id);

	List<Institution> findAll();
	
	Institution save(Institution institution);
	
	List<Institution> save(List<Institution> institution);
	
	Institution delete(Long id);
	
	void delete(List<Long> ids);
}
