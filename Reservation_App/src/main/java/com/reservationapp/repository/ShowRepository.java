package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{

}
