package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Reservation;
import com.reservationapp.repository.ReservationRepository;
import com.reservationapp.service.ReservationService;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation findOne(Long id) {
		return reservationRepository.findById(id).get();
	}
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> save(List<Reservation> reservation) {
		return reservationRepository.saveAll(reservation);
	}

	@Override
	public Reservation delete(Long id) {
		Reservation reservation = reservationRepository.findById(id).get();
		if(reservation == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		reservationRepository.delete(reservation);
		return reservation;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
}
