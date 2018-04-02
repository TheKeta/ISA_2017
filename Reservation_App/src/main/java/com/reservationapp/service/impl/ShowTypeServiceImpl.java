package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.ShowType;
import com.reservationapp.repository.ShowTypeRepository;
import com.reservationapp.service.ShowTypeService;

@Service
@Transactional
public class ShowTypeServiceImpl implements ShowTypeService{

	@Autowired
	private ShowTypeRepository showTypeRepository;

	@Override
	public ShowType findOne(Long id) {
		return showTypeRepository.findById(id).get();
	}
	
	@Override
	public List<ShowType> findAll() {
		return showTypeRepository.findAll();
	}

	@Override
	public ShowType save(ShowType showType) {
		return showTypeRepository.save(showType);
	}

	@Override
	public List<ShowType> save(List<ShowType> showType) {
		return showTypeRepository.saveAll(showType);
	}

	@Override
	public ShowType delete(Long id) {
		ShowType showType = showTypeRepository.findById(id).get();
		if(showType == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		showTypeRepository.delete(showType);
		return showType;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
}
