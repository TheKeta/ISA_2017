package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Message;
import com.reservationapp.repository.MessageRepository;
import com.reservationapp.service.MessageService;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message findOne(Long id) {
		return messageRepository.findById(id).get();
	}

	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Transactional(readOnly = false)
	public Message save(Message message) {
		return messageRepository.save(message);
	}

	@Transactional(readOnly = false)
	public List<Message> save(List<Message> message) {
		return messageRepository.saveAll(message);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Message delete(Long id) {
		Message message = messageRepository.findById(id).get();
		if(message == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite");
		}
		messageRepository.delete(message);
		return message;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
		
	}
	
	@Override
	public List<Message> findRMSG(Long id){
		return messageRepository.findRMSG(id);
	}
	
	@Override
	public List<Message> findSMSG(Long id){
		return messageRepository.findSMSG(id);
	}

}
