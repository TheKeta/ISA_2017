package com.reservationapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;

public interface EventRepository extends JpaRepository<Event, Long>{

	
	@Query("SELECT p FROM Event p where lower(p.hall) = lower(:hall)")
	public List<Event> findByHall(@Param("hall") Hall hall);

	@Query("SELECT p FROM Event p where p.eventDate between :fromDate and :toDate")
	public List<Event> findByStartDateBetween(@Param(value = "fromDate") Date fromDate, @Param(value="toDate") Date toDate);
}
