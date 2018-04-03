package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Institution;
import com.reservationapp.model.InstitutionType;

public interface InstitutionService {

	Institution findOne(Long id);

	List<Institution> findAll();
	
	Institution save(Institution institution);
	
	List<Institution> save(List<Institution> institution);
	
	Institution delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Institution> searchByType(String type);

	List<Institution> searchByNameAndType(InstitutionType type, String name);
}
