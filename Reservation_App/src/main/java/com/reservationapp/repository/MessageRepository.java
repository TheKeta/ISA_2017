package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Message;

public interface MessageRepository  extends JpaRepository<Message, Long>{

}
