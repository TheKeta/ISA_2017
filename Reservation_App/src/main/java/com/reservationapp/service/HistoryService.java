package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.History;
import com.reservationapp.model.User;

public interface HistoryService {

	History  findOneById(Long id);
	
	History findByUser(User user);
	
	List<History> findAll();
	
	History save(History history);
}
