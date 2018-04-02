package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long>{

}
