package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Message;

public interface MessageService {
	Message findOne(Long id);

	List<Message> findAll();
	
	Message save(Message message);
	
	List<Message> save(List<Message> message);
	
	Message delete(Long id);
	
	void delete(List<Long> ids);
	
}
