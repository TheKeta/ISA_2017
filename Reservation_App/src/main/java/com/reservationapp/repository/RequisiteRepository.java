package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Requisite;

public interface RequisiteRepository extends JpaRepository<Requisite, Long>{

	@Query("SELECT r FROM Requisite r where lower(r.type) = lower(:type)")
	public List<Requisite> findAllUsersReqs(@Param("type") String type);
}
