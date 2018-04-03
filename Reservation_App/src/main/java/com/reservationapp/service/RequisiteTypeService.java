package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.RequisiteType;

public interface RequisiteTypeService {
	RequisiteType findOne(Long id);

	List<RequisiteType> findAll();
	
	RequisiteType save(RequisiteType requisiteType);
	
	List<RequisiteType> save(List<RequisiteType> requisiteType);
	
	RequisiteType delete(Long id);
	
	void delete(List<Long> ids);
}
