package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.InstitutionType;

public interface InstitutionTypeService {

	InstitutionType findOne(Long id);

	List<InstitutionType> findAll();
	
	InstitutionType save(InstitutionType institutionType);
	
	List<InstitutionType> save(List<InstitutionType> institutionTypes);
	
	InstitutionType delete(Long id);
	
	void delete(List<Long> ids);
	
	InstitutionType findByName(String name);
}
 