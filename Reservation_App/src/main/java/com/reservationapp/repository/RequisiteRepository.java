package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reservationapp.model.Requisite;

public interface RequisiteRepository extends JpaRepository<Requisite, Long>{

//	@Query("SELECT * FROM REQUISITE")
//	public List<Requisite> findAll();
}
