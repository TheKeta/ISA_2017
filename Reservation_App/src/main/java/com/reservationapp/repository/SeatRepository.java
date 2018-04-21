package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Hall;
import com.reservationapp.model.Seat;
import com.reservationapp.model.SeatType;

public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT p FROM Seat p where lower(p.hall) = lower(:hall)")
	public List<Seat> findByHall(@Param("hall") Hall hall);
	
	public Seat findByRowAndSeatNumberAndHallAndSeatType(int row, int seatNumber, Hall hall, SeatType seatType);
}
