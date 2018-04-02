package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Hall;
import com.reservationapp.repository.HallRepository;
import com.reservationapp.service.HallService;

@Service
@Transactional
public class HallServiceImpl implements HallService{

	@Autowired
	private HallRepository hallRepository;

	@Override
	public Hall findOne(Long id) {
		return hallRepository.findById(id).get();
	}
	
	@Override
	public List<Hall> findAll() {
		return hallRepository.findAll();
	}

	@Override
	public Hall save(Hall hall) {
		return hallRepository.save(hall);
	}

	@Override
	public List<Hall> save(List<Hall> hall) {
		return hallRepository.saveAll(hall);
	}

	@Override
	public Hall delete(Long id) {
		Hall hall = hallRepository.findById(id).get();
		if(hall == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		hallRepository.delete(hall);
		return hall;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
}
