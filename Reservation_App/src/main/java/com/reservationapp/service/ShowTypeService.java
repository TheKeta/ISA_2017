package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.ShowType;

public interface ShowTypeService {
	
	ShowType findOne(Long id);

	List<ShowType> findAll();
	
	ShowType save(ShowType showType);
	
	List<ShowType> save(List<ShowType> showTypes);
	
	ShowType delete(Long id);
	
	void delete(List<Long> ids);
}
