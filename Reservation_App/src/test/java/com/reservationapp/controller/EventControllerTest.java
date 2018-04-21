package com.reservationapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Show;

import main.TestUtil;
import test.constants.EventConstants;
import test.constants.RequisiteConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventControllerTest {

	private static final String URL_PREFIX = "/event";
	

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	
	private MockMvc mockMvc;
	
	
	void testAddEvent() throws Exception {
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
	void testDelete() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk());

	}

}
