package com.reservationapp.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.reservationapp.model.Message;

import main.TestUtil;
import test.constants.BidConstants;
import test.constants.MessageConstants;

class MessageControllerTest {

	private static final String URL_PREFIX = "/message";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	
	@Test
	void testGetBid() throws Exception {
		mockMvc.perform(get(URL_PREFIX + BidConstants.DB_ID)).andExpect(status().isOk())
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
	void testSend() throws Exception {
		Message msg = new Message();
		msg.setId(MessageConstants.DB_ID);
		msg.setRead(MessageConstants.DB_READ);
		msg.setReciverID(MessageConstants.DB_ID);
		msg.setSenderID(MessageConstants.DB_ID);
		msg.setText(MessageConstants.DB_TEXT);
		
		String json = TestUtil.json(msg);
		this.mockMvc.perform(post(URL_PREFIX + "/send/aa@aa").content(json)).andExpect(status().isOk());
		
	}

	@Test
	void testGetRM()throws Exception {
		mockMvc.perform(get(URL_PREFIX +"/getRMessages")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(MessageConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].senderid").value(hasItem(MessageConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].reciverid").value(hasItem(MessageConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].text").value(hasItem(MessageConstants.DB_TEXT)))
		.andExpect(jsonPath("$.[*].read").value(hasItem(MessageConstants.DB_READ)));
	}

	@Test
	void testGetSM() throws Exception {
		mockMvc.perform(get(URL_PREFIX +"/getSMessages")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(MessageConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].senderid").value(hasItem(MessageConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].reciverid").value(hasItem(MessageConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].text").value(hasItem(MessageConstants.DB_TEXT)))
		.andExpect(jsonPath("$.[*].read").value(hasItem(MessageConstants.DB_READ)));
	}

}
