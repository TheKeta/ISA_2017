package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Institution;
import com.reservationapp.model.InstitutionRating;

public interface InstitutionRatingRepository extends JpaRepository<InstitutionRating, Long>{

	
	@Query("SELECT p FROM InstitutionRating p where lower(p.institution) = lower(:institution)")
	public List<InstitutionRating> searchByInstitution(@Param("institution") Institution institution);
}
