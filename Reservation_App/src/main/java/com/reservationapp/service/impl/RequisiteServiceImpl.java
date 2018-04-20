package com.reservationapp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Requisite;
import com.reservationapp.repository.RequisiteRepository;
import com.reservationapp.service.RequisiteService;

@Service
@Transactional(readOnly = true)
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

	@Transactional(readOnly = false)
	public Requisite save(Requisite requisite) {
		return requisiteRepository.save(requisite);
	}

	@Transactional(readOnly = false)
	public List<Requisite> save(List<Requisite> requisite) {
		return requisiteRepository.saveAll(requisite);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Requisite delete(Long id) {
		Requisite requisite = requisiteRepository.findById(id).get();
		if(requisite == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite");
		}
		requisiteRepository.delete(requisite);
		return requisite;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
	
	@Override
	public List<Requisite> findAllUserReqs(String type){
		return requisiteRepository.findAllUsersReqs(type);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Requisite update(Requisite requisite) {
		Requisite req = findOne(requisite.getId()); 
		req.setPrice(requisite.getPrice());
		requisiteRepository.save(req);
		return req;
	}
	
	@Override
	public List<Requisite> findByEndDateLessThanAndIsActiveTrue(Date date){
		return requisiteRepository.findByEndDateLessThanAndIsActiveTrue(date);
	}
	
	@Override
	public List<Requisite> findAllUserFreshReqs(){
		return requisiteRepository.findAllUserFreshReqs();
	}
}
