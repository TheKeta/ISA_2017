package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Friendship;

public interface FriendshipService {

	Friendship findOneById(Long id);
	
	List<Friendship> acceptedFriendships();
	
	//odbijena
	List<Friendship> rejectedFriendships();
	
	//zahtevi na koje jos nije odgovoreno
	List<Friendship> friedshipRequests();
	
	List<Friendship> findAll();
	
	Friendship save(Friendship friendship);
}
