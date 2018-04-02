package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Institution;
import com.reservationapp.repository.InstitutionRepository;
import com.reservationapp.service.InstitutionService;

@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {
	

	@Autowired
	private InstitutionRepository institutionRepository;

	@Override
	public Institution findOne(Long id) {
		return institutionRepository.findById(id).get();
	}
	
	@Override
	public List<Institution> findAll() {
		return institutionRepository.findAll();
	}

	@Override
	public Institution save(Institution institution) {
		return institutionRepository.save(institution);
	}

	@Override
	public List<Institution> save(List<Institution> institution) {
		return institutionRepository.saveAll(institution);
	}

	@Override
	public Institution delete(Long id) {
		Institution institution = institutionRepository.findById(id).get();
		if(institution == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		institutionRepository.delete(institution);
		return institution;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

}
