package com.reservationapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservationapp.model.Bid;
import com.reservationapp.model.Requisite;
import com.reservationapp.repository.BidRepository;
import com.reservationapp.service.BidService;

@Service
@Transactional
public class BidServiceImpl implements BidService{
	
	@Autowired
	private BidRepository bidRepository;

	@Override
	public Bid findOne(Long id) {
		return bidRepository.findById(id).get();
	}

	@Override
	public List<Bid> findAll() {
		return bidRepository.findAll();
	}

	@Override
	public Bid save(Bid bid) {
		return bidRepository.save(bid);
	}

	@Override
	public List<Bid> save(List<Bid> bid) {
		return bidRepository.saveAll(bid);

	}

	@Override
	public Bid delete(Long id) {
		Bid bid = bidRepository.findById(id).get();
		if(bid == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite");
		}
		bidRepository.delete(bid);
		return bid;
	}
	
	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
		
	}

	
		
	

}
