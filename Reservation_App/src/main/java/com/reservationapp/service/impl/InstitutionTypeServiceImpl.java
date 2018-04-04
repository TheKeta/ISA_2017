package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.InstitutionType;
import com.reservationapp.repository.InstitutionTypeRepository;
import com.reservationapp.service.InstitutionTypeService;


@Service
@Transactional
public class InstitutionTypeServiceImpl implements InstitutionTypeService{

	@Autowired
	private InstitutionTypeRepository institutionTypeRepository;

	@Override
	public InstitutionType findOne(Long id) {
		return institutionTypeRepository.findById(id).get();
	}
	
	@Override
	public List<InstitutionType> findAll() {
		return institutionTypeRepository.findAll(); 
	}

	@Override
	public InstitutionType save(InstitutionType institutionType) {
		return institutionTypeRepository.save(institutionType);
	}

	@Override
	public List<InstitutionType> save(List<InstitutionType> institutionType) {
		return institutionTypeRepository.saveAll(institutionType);
	}

	@Override
	public InstitutionType delete(Long id) {
		InstitutionType institutionType = institutionTypeRepository.findById(id).get();
		if(institutionType == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		institutionTypeRepository.delete(institutionType);
		return institutionType;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public InstitutionType findByName(String name) {
		return institutionTypeRepository.findByName(name);
	}
}
