package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long>{

	@Query("SELECT p FROM SeatType p where lower(p.name) = lower(:name)")
	public SeatType findByName(@Param("name") String name);
}
