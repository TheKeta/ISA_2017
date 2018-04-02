package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.InstitutionType;

public interface InstitutionTypeRepository extends JpaRepository<InstitutionType, Long>{

}
