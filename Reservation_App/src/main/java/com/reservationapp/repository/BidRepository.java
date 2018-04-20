package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservationapp.model.Bid;

public interface BidRepository  extends JpaRepository<Bid, Long>{
	
	@Query("SELECT r FROM Bid r where lower(r.itemsID) = lower(:itemsID)")
	public  List<Bid> findHeighestBid(@Param("itemsID") Long itemsID);

}
