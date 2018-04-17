package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;

public interface HallRepository extends JpaRepository<Hall, Long>{

	@Query("SELECT p FROM Hall p where lower(p.institution) = lower(:institution)")
	public List<Hall> findByInstitution(@Param("institution") Institution institution);
}
