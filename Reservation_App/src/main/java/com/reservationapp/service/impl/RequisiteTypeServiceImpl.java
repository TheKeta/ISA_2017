package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reservationapp.model.RequisiteType;
import com.reservationapp.repository.RequisiteTypeRepository;
import com.reservationapp.service.RequisiteTypeService;

public class RequisiteTypeServiceImpl implements RequisiteTypeService {

	@Autowired
	private RequisiteTypeRepository requisiteTypeRepository;
	
	@Override
	public RequisiteType findOne(Long id) {
		return requisiteTypeRepository.findById(id).get();
	}

	@Override
	public List<RequisiteType> findAll() {
		return requisiteTypeRepository.findAll();
	}

	@Override
	public RequisiteType save(RequisiteType requisiteType) {
		return requisiteTypeRepository.save(requisiteType);
	}

	@Override
	public List<RequisiteType> save(List<RequisiteType> requisiteType) {
		return requisiteTypeRepository.saveAll(requisiteType);
	}

	@Override
	public RequisiteType delete(Long id) {
		RequisiteType requisiteType = requisiteTypeRepository.findById(id).get();
		if(requisiteType == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite type");
		}
		requisiteTypeRepository.delete(requisiteType);
		return requisiteType;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

}
