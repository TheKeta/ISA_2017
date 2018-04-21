package com.reservationapp.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Bid;

import test.constants.BidConstants;

class BidServiceImplTest {
	
	@Autowired
	private BidServiceImpl bidS;

	@Test
	void testFindOne() {
		Bid req = bidS.findOne(BidConstants.DB_ID);
		assertThat(req).isNotNull();
		assertThat(req.getId()).isEqualTo(BidConstants.DB_ID);
		assertThat(req.getBiddersID()).isEqualTo(BidConstants.DB_BIDDERSID);
        assertThat(req.getItemsID()).isEqualTo(BidConstants.DB_ITEMSID);
        assertThat(req.getPrice()).isEqualTo(BidConstants.DB_PRICE);  
        assertThat(req.isReservation()).isEqualTo(BidConstants.DB_RESERVATION);  
	}

	@Test
	void testFindAll() {
		List<Bid> req = bidS.findAll();
		assertThat(req).hasSize(2);
	}

	@Test
	@Transactional
    @Rollback(true) 
	void testSaveBid() {
		Bid req = new Bid();
		req.setBiddersID(BidConstants.DB_BIDDERSID);
		req.setItemsID(BidConstants.DB_ITEMSID);
		req.setPrice(BidConstants.DB_PRICE);
		req.setReservation(BidConstants.DB_RESERVATION);
		
		int dbSizeBeforeAdd = bidS.findAll().size();
		
		Bid re = bidS.save(req);
		assertThat(re).isNotNull();
		
		List<Bid> reqs = bidS.findAll();
		assertThat(reqs).hasSize(dbSizeBeforeAdd + 1);
	    re = reqs.get(reqs.size() - 1);
	    
	    assertThat(re.getId()).isEqualTo(BidConstants.DB_ID);
		assertThat(re.getBiddersID()).isEqualTo(BidConstants.DB_BIDDERSID);
        assertThat(re.getItemsID()).isEqualTo(BidConstants.DB_ITEMSID);
        assertThat(re.getPrice()).isEqualTo(BidConstants.DB_PRICE); 
        assertThat(re.isReservation()).isEqualTo(BidConstants.DB_RESERVATION); 
	}

	

	@Test
	@Transactional
    @Rollback(true) 
	void testDeleteLong() {
		int dbSizeBeforeRemove = bidS.findAll().size();
		bidS.delete(1L);
		
		List<Bid> students = bidS.findAll();
		assertThat(students).hasSize(dbSizeBeforeRemove - 1);
		
		Bid dbStudent = bidS.findOne(1L);
		assertThat(dbStudent).isNull();
	}



	@Test
	void testFindHeighestBid() {
		Bid req = bidS.findHeighestBid(BidConstants.DB_ITEMSID);
		assertThat(req).isNotNull();
		assertThat(req.getId()).isEqualTo(BidConstants.DB_ID);
		assertThat(req.getBiddersID()).isEqualTo(BidConstants.DB_BIDDERSID);
        assertThat(req.getItemsID()).isEqualTo(BidConstants.DB_ITEMSID);
        assertThat(req.getPrice()).isEqualTo(BidConstants.DB_PRICE);  
        assertThat(req.isReservation()).isEqualTo(BidConstants.DB_RESERVATION);  
	}

	@Test
	void testFindByItemsid() {
		List<Bid> req = bidS.findByItemsid(BidConstants.DB_ITEMSID);
		assertThat(req).hasSize(2);
	}

}
