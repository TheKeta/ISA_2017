package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Seat;
import com.reservationapp.repository.SeatRepository;
import com.reservationapp.service.SeatService;

@Service
@Transactional
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatRepository seatRepository;

	@Override
	public Seat findOne(Long id) {
		return seatRepository.findById(id).get();
	}
	
	@Override
	public List<Seat> findAll() {
		return seatRepository.findAll();
	}

	@Override
	public Seat save(Seat seat) {
		return seatRepository.save(seat);
	}

	@Override
	public List<Seat> save(List<Seat> seat) {
		return seatRepository.saveAll(seat);
	}

	@Override
	public Seat delete(Long id) {
		Seat seat = seatRepository.findById(id).get();
		if(seat == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		seatRepository.delete(seat);
		return seat;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
}
