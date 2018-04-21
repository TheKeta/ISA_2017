package com.reservationapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.User;
import com.reservationapp.model.UserType;
import com.reservationapp.repository.UserRepository;
import com.reservationapp.repository.UserTypeRepository;
import com.reservationapp.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
//	
//	@Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findOneById(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		User res = userRepository.save(user);
		
		return res;
	}

	@Override
	public List<User> save(List<User> users) {
		return userRepository.saveAll(users);
	}

	@Override
	public User delete(Long id) {
		User user = userRepository.findById(id).get();
		if(user == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		userRepository.delete(user);
		return user;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public User findOneByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findOneByEmail(email);
	}
	
	@Override
	public User findOneByToken(String token){
		return userRepository.findOneByToken(token);
	}

	@Override
	public List<User> findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWith(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return userRepository.findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWith(firstName, lastName);
	}


	
}
