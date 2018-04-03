package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Institution;
import com.reservationapp.model.InstitutionType;

public interface InstitutionRepository extends JpaRepository<Institution, Long>{

	@Query("SELECT p FROM Institution p where lower(p.type) = lower(:type)")
	public List<Institution> findByType(@Param("type") InstitutionType type);
	
	@Query("SELECT p FROM Institution p where lower(p.type) = lower(:type) and lower(p.name) = lower(:name)")
	public List<Institution> findByNameAndType(@Param("type") InstitutionType type, @Param("name") String name);
}
