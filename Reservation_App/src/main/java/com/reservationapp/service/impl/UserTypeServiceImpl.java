package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.UserType;
import com.reservationapp.repository.UserTypeRepository;
import com.reservationapp.service.UserTypeService;

@Transactional
@Service
public class UserTypeServiceImpl implements UserTypeService{

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public UserType findOne(Long id) {
		return userTypeRepository.findById(id).get();
	}
	
	@Override
	public List<UserType> findAll() {
		return userTypeRepository.findAll();
	}

	@Override
	public UserType save(UserType userType) {
		return userTypeRepository.save(userType);
	}

	@Override
	public List<UserType> save(List<UserType> userTypes) {
		return userTypeRepository.saveAll(userTypes);
	}

	@Override
	public UserType delete(Long id) {
		UserType userType = userTypeRepository.findById(id).get();
		if(userType == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user type");
		}
		userTypeRepository.delete(userType);
		return userType;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	
}
