package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservationapp.model.Requisite;
import com.reservationapp.repository.RequisiteRepository;
import com.reservationapp.service.RequisiteService;

@Service
public class RequisiteServiceImpl implements RequisiteService{
	
	@Autowired
	private RequisiteRepository requisiteRepository;

	@Override
	public Requisite findOne(Long id) {
		return requisiteRepository.findById(id).get();
	}

	@Override
	public List<Requisite> findAll() {
		return requisiteRepository.findAll();
	}

	@Override
	public Requisite save(Requisite requisite) {
		return requisiteRepository.save(requisite);
	}

	@Override
	public List<Requisite> save(List<Requisite> requisite) {
		return requisiteRepository.saveAll(requisite);
	}

	@Override
	public Requisite delete(Long id) {
		Requisite requisite = requisiteRepository.findById(id).get();
		if(requisite == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite");
		}
		requisiteRepository.delete(requisite);
		return requisite;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
	
	@Override
	public List<Requisite> findAllUserReqs(String type){
		return requisiteRepository.findAllUsersReqs(type);
	}
}
