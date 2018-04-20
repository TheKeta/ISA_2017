package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.SeatType;
import com.reservationapp.repository.SeatTypeRepository;
import com.reservationapp.service.SeatTypeService;

@Service
@Transactional
public class SeatTypeServiceImpl implements SeatTypeService{

	@Autowired
	private SeatTypeRepository seatTypeRepository;

	@Override
	public SeatType findOne(Long id) {
		return seatTypeRepository.findById(id).get();
	}
	
	@Override
	public List<SeatType> findAll() {
		return seatTypeRepository.findAll();
	}

	@Override
	public SeatType save(SeatType seatType) {
		return seatTypeRepository.save(seatType);
	}

	@Override
	public List<SeatType> save(List<SeatType> seatType) {
		return seatTypeRepository.saveAll(seatType);
	}

	@Override
	public SeatType delete(Long id) {
		SeatType seatType = seatTypeRepository.findById(id).get();
		if(seatType == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		seatTypeRepository.delete(seatType);
		return seatType;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public SeatType findByName(String name) {
		return seatTypeRepository.findByName(name);
	}
}
