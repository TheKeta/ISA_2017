package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Bid;

public interface BidService {
	Bid findOne(Long id);

	List<Bid> findAll();
	
	Bid save(Bid bid);
	
	List<Bid> save(List<Bid> bid);
	
	Bid delete(Long id);
	
	void delete(List<Long> ids);
	
	Bid findHeighestBid(Bid bid);
	
	List<Bid> findByItemsid(Long itemsid);
	
}
