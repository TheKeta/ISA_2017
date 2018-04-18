package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Bid;

public interface BidRepository  extends JpaRepository<Bid, Long>{


}
