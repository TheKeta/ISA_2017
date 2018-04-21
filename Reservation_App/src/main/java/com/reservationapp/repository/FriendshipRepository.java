package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Friendship;

public interface FriendshipRepository extends JpaRepository<Friendship, Long>{

	//prihvacena prijateljstva
	List<Friendship> findByAcceptedIs(int accepted);
	
}
