package com.reservationapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;
import com.reservationapp.repository.ReservationRepository;
import com.reservationapp.service.ReservationService;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@Autowired
	private HallServiceImpl hallService;
	
	@Autowired
	private SeatServiceImpl seatService;


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

	@Override
	public List<Reservation> findByInstitution(Long id) {
		List<Hall> halls = hallService.findByInstitution(institutionService.findOne(id));
		List<Reservation> reservations = new ArrayList<Reservation>();
		for(Hall h : halls) {
			for(Seat s : seatService.findByHall(h)) {
				Reservation reservation = reservationRepository.findBySeat(s);
				if(reservation != null && reservation.getUser() == null) {
					reservations.add(reservation);	
				}
				
			}
		}
		return reservations;
	}

	public List<Reservation> findByEvent(Event event) {
		return reservationRepository.findByEvent(event);
	}
}
