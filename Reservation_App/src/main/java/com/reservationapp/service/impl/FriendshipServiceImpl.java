package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Friendship;
import com.reservationapp.repository.FriendshipRepository;
import com.reservationapp.service.FriendshipService;

@Service
@Transactional
public class FriendshipServiceImpl implements FriendshipService{

	@Autowired
	private FriendshipRepository friendshipRepository;
	
	@Override
	public Friendship findOneById(Long id) {
		// TODO Auto-generated method stub
		return friendshipRepository.findById(id).get();
	}

	@Override
	public List<Friendship> acceptedFriendships() {
		// TODO Auto-generated method stub
		return friendshipRepository.findByAcceptedIs(2);
	}

	@Override
	public List<Friendship> rejectedFriendships() {
		// TODO Auto-generated method stub
		return friendshipRepository.findByAcceptedIs(1);
	}

	@Override
	public List<Friendship> friedshipRequests() {
		// TODO Auto-generated method stub
		return friendshipRepository.findByAcceptedIs(0);
	}

	@Override
	public List<Friendship> findAll() {
		// TODO Auto-generated method stub
		return friendshipRepository.findAll();
	}

	public Friendship save(Friendship friendship) {
		// TODO Auto-generated method stub
		return friendshipRepository.save(friendship);
	}

}
