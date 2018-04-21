package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Message;

public interface MessageRepository  extends JpaRepository<Message, Long>{

	@Query("SELECT r FROM Message r where r.reciverID = :id")
	List<Message> findRMSG(@Param("id") Long id);
	
	@Query("SELECT r FROM Message r where r.senderID = :id and r.reciverID <> r.senderID")
	List<Message> findSMSG(@Param("id") Long id);

}
