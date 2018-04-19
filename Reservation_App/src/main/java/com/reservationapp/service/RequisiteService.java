package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Requisite;

public interface RequisiteService {
	Requisite findOne(Long id);

	List<Requisite> findAll();
	
	Requisite save(Requisite requisite);
	
	List<Requisite> save(List<Requisite> requisite);
	
	Requisite delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Requisite> findAllUserReqs(String type);
	
	Requisite update(Requisite requisite);
}
