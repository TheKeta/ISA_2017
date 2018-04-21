package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.History;

public interface HistoryRepository extends JpaRepository<History, Long>{

}
