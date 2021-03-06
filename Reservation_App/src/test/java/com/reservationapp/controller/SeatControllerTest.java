package com.reservationapp.controller;

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

import com.reservationapp.model.Hall;
import com.reservationapp.model.Seat;
import com.reservationapp.model.SeatType;

import main.TestUtil;
import test.constants.EventConstants;
import test.constants.SeatConstants;

public class SeatControllerTest {
	
private static final String URL_PREFIX = "/seat";
	

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
	public void testGetSeats()throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/getSeats/" + EventConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].seats").value(SeatConstants.DB_SEATS))
		.andExpect(jsonPath("$.[*].halls").value(SeatConstants.DB_HALLS));
	}

	@Test
	public void testGetSeat() throws Exception{
		this.mockMvc.perform(get(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(SeatConstants.DB_ID))
		.andExpect(jsonPath("$.[*].row").value(SeatConstants.DB_ROW))
		.andExpect(jsonPath("$.[*].seatNumber").value(SeatConstants.DB_SEAT_NUMBER))
		.andExpect(jsonPath("$.[*].hall").value(SeatConstants.DB_HALL))
		.andExpect(jsonPath("$.[*].seatType").value(SeatConstants.DB_SEAT_TYPE));
	}

	@Test
	public void testAddSeat() throws Exception {
		Seat seat = new Seat();
		seat.setHall(new Hall());
		seat.setRow(1);
		seat.setSeatNumber(1);
		seat.setSeatType(new SeatType());
		
		String json = TestUtil.json(seat);
		this.mockMvc.perform(post(URL_PREFIX).content(json)).andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception{
		this.mockMvc.perform(delete(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk());
	}

}
