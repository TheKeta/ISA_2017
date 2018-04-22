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

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.reservationapp.model.Genre;

import main.TestUtil;
import test.constants.HallConstants;

public class HallControllerTest {
	
	private static final String URL_PREFIX = "/hall";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetHalls() throws Exception{
		mockMvc.perform(get(URL_PREFIX + "/getHalls/" + HallConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].name").value(hasItem(HallConstants.DB_NAME)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(HallConstants.DB_ID.intValue())));
	}

	@Test
	public void testGetHall() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + HallConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].seat.id").value(hasItem(HallConstants.DB_SEAT_ID)))
		.andExpect(jsonPath("$.[*].seat.row").value(hasItem(HallConstants.DB_SEAT_ROW)))
		.andExpect(jsonPath("$.[*].seat.seatNumber").value(hasItem(HallConstants.DB_SEAT_NUMBER)))
		.andExpect(jsonPath("$.[*].seat.hall").value(hasItem(HallConstants.DB_HALL)))
		.andExpect(jsonPath("$.[*].seat.seatType").value(hasItem(HallConstants.DB_TYPE)));
	}


	@Test
	public void testAddHall() throws Exception {
		Genre genre = new Genre();
		genre.setName(HallConstants.NEW_NAME);
		String json = TestUtil.json(genre);
		this.mockMvc.perform(post(URL_PREFIX).content(json)).andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception{
		this.mockMvc.perform(delete(URL_PREFIX + "/" + HallConstants.DB_ID)).andExpect(status().isOk());
	}

}
