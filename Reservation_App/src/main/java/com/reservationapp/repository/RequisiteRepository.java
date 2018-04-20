package com.reservationapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Requisite;

public interface RequisiteRepository extends JpaRepository<Requisite, Long>{

	@Query("SELECT r FROM Requisite r where r.isActive = true and lower(r.type) = lower(:type) order by r.id desc ")
	public List<Requisite> findAllUsersReqs(@Param("type") String type);

	@Query("SELECT r FROM Requisite r where r.isActive = true and r.endDate <= :date ")
	public List<Requisite> findByEndDateLessThanAndIsActiveTrue(@Param("date") Date date);

	
}
