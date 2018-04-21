package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservationapp.model.History;
import com.reservationapp.model.User;
import com.reservationapp.repository.HistoryRepository;
import com.reservationapp.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public History findOneById(Long id) {
		// TODO Auto-generated method stub
		return historyRepository.findById(id).get();
	}

	@Override
	public List<History> findAll() {
		// TODO Auto-generated method stub
		return historyRepository.findAll();
	}

	@Override
	public History save(History history) {
		// TODO Auto-generated method stub
		return historyRepository.save(history);
	}

	@Override
	public History findByUser(User user) {
		// TODO Auto-generated method stub
		return historyRepository.findOneByUser(user);
	}

}
