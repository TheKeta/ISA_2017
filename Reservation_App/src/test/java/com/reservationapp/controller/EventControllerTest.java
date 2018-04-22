package com.reservationapp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Show;

import main.TestUtil;
import test.constants.EventConstants;

public class EventControllerTest {

	private static final String URL_PREFIX = "/event";
	

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
	public void testAddEvent() throws Exception {
		Event event = new Event();
		event.setEventDate(new Date());
		event.setHall(new Hall());
		event.setPrice(100);
		event.setShow(new Show());
		event.setId(EventConstants.DB_ID);
		
		String json = TestUtil.json(event);
		this.mockMvc.perform(post(URL_PREFIX).content(json)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDelete() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk());
	}

	@Test
	public void testGetEvents() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/events/" + EventConstants.DB_ID)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$.[*].id").value(EventConstants.DB_ID))
				.andExpect(jsonPath("$.[*].hall").value(EventConstants.DB_HALL))
				.andExpect(jsonPath("$.[*].show").value(EventConstants.DB_SHOW))
				.andExpect(jsonPath("$.[*].eventDate").value(EventConstants.DB_EVENTDATE))
				.andExpect(jsonPath("$.[*].price").value(EventConstants.DB_PRICE));
	}

	@Test
	public void testGetEventSeats() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/getEventSeats/" + EventConstants.DB_ID)).andExpect(status().isOk())
			.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$.[*].event").value(EventConstants.DB_EVENT))
			.andExpect(jsonPath("$.[*].seats").value(EventConstants.DB_SEATS))
			.andExpect(jsonPath("$.[*].reservations").value(EventConstants.DB_RESERVATIONS));
	}

	@Test
	public void testGetEventShows() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$.[*].event").value(EventConstants.DB_EVENT))
		.andExpect(jsonPath("$.[*].shows").value(EventConstants.DB_SHOWS));
	}

	@Test
	public void testGetEventShowsHalls() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/eventShowsHalls/" + EventConstants.DB_ID)).andExpect(status().isOk())
			.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$.[*].event").value(EventConstants.DB_EVENT))
			.andExpect(jsonPath("$.[*].shows").value(EventConstants.DB_SHOWS))
			.andExpect(jsonPath("$.[*].halls").value(EventConstants.DB_HALLS));
		;
	}

	@Test
	public void testGetEvent() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/getEvent/" + EventConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(EventConstants.DB_ID))
		.andExpect(jsonPath("$.[*].hall").value(EventConstants.DB_HALL))
		.andExpect(jsonPath("$.[*].show").value(EventConstants.DB_SHOW))
		.andExpect(jsonPath("$.[*].eventDate").value(EventConstants.DB_EVENTDATE))
		.andExpect(jsonPath("$.[*].price").value(EventConstants.DB_PRICE));
	}
	
	@Test
	public void testGetInstitutionEvents() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/getEvents/" + EventConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(3)))
		.andExpect(jsonPath("$.[*].groups").value(EventConstants.DB_EVENT));
	}

}
