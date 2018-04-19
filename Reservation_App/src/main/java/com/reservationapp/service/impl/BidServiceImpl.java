package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservationapp.model.Bid;
import com.reservationapp.repository.BidRepository;
import com.reservationapp.service.BidService;

@Service
@Transactional(readOnly = true)
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

	@Transactional(readOnly = false)
	public Bid save(Bid bid) {
		return bidRepository.save(bid);
	}

	@Transactional(readOnly = false)
	public List<Bid> save(List<Bid> bid) {
		return bidRepository.saveAll(bid);

	}

	@Transactional(readOnly = false)
	public Bid delete(Long id) {
		Bid bid = bidRepository.findById(id).get();
		if(bid == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant requisite");
		}
		bidRepository.delete(bid);
		return bid;
	}
	
	@Transactional(readOnly = false)
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
		
	}

	
		
	

}
