package com.reservationapp.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Bid;

import main.TestUtil;
import test.constants.BidConstants;

class BidControllerTest {
	
	private static final String URL_PREFIX = "/bid";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	
	private MockMvc mockMvc;

	
	
	@Test
	void testGetBids() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getBids")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(BidConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].itemsiid").value(hasItem(BidConstants.DB_ITEMSID.intValue())))
		.andExpect(jsonPath("$.[*].biddersid").value(hasItem(BidConstants.DB_BIDDERSID.intValue())))
		.andExpect(jsonPath("$.[*].price").value(hasItem(BidConstants.DB_PRICE)))
		.andExpect(jsonPath("$.[*].reservation").value(hasItem(BidConstants.DB_RESERVATION)));
	}

	@Test
	void testGetBid() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getBid/"+BidConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(BidConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].itemsiid").value(hasItem(BidConstants.DB_ITEMSID.intValue())))
		.andExpect(jsonPath("$.[*].biddersid").value(hasItem(BidConstants.DB_BIDDERSID.intValue())))
		.andExpect(jsonPath("$.[*].price").value(hasItem(BidConstants.DB_PRICE)))
		.andExpect(jsonPath("$.[*].reservation").value(hasItem(BidConstants.DB_RESERVATION)));
	}

	@Test
	@Transactional
	@Rollback(true)
	void testAddBid()  throws Exception {
		Bid bid = new Bid();
		bid.setId(BidConstants.DB_ID);
		bid.setBiddersID(BidConstants.DB_BIDDERSID);
		bid.setItemsID(BidConstants.DB_ITEMSID);
		bid.setPrice(BidConstants.DB_PRICE);
		bid.setReservation(BidConstants.DB_RESERVATION);
		
		String json = TestUtil.json(bid);
		this.mockMvc.perform(post(URL_PREFIX + "/addNewBid").content(json)).andExpect(status().isOk());
		
	}

	@Test
	@Transactional
	@Rollback(true)
	void testAddBidd()  throws Exception {
		Bid bid = new Bid();
		bid.setId(BidConstants.DB_ID);
		bid.setBiddersID(BidConstants.DB_BIDDERSID);
		bid.setItemsID(BidConstants.DB_ITEMSID);
		bid.setPrice(BidConstants.DB_PRICE);
		bid.setReservation(BidConstants.DB_RESERVATION);
		
		String json = TestUtil.json(bid);
		this.mockMvc.perform(post(URL_PREFIX + "/order").content(json)).andExpect(status().isOk());
	}
	
	

	@Test
	@Transactional
	@Rollback(true)
	void testDelete()throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" +BidConstants.DB_ID)).andExpect(status().isOk());

	}

}
