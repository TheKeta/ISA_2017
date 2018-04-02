package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
