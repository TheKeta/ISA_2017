package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
