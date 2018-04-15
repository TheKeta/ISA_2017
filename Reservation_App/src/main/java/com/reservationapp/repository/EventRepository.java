package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Event;
import com.reservationapp.model.Institution;

public interface EventRepository extends JpaRepository<Event, Long>{

	
	@Query("SELECT p FROM Event p where lower(p.institution) = lower(:institution)")
	public List<Event> findByInstitution(@Param("institution") Institution institution);

}
