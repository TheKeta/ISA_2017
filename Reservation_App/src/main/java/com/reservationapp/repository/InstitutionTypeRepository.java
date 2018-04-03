package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.InstitutionType;

public interface InstitutionTypeRepository extends JpaRepository<InstitutionType, Long>{

	@Query("SELECT p FROM InstitutionType p where lower(p.name) = lower(:name)")
	public InstitutionType findByName(@Param("name") String name);
}
