package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.History;

public interface HistoryService {

	History  findOneById(Long id);
	
	List<History> findAll();
	
	History save(History history);
}
