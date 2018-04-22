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

import com.reservationapp.model.Genre;
import com.reservationapp.model.Show;
import com.reservationapp.model.ShowType;

import main.TestUtil;
import test.constants.EventConstants;
import test.constants.ShowConstants;

public class ShowControllerTest {
	
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
	public void testGetShows() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX + "/getShows")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].show.id").value(ShowConstants.DB_ID))
		.andExpect(jsonPath("$.[*].show.name").value(ShowConstants.DB_NAME))
		.andExpect(jsonPath("$.[*].show.genre").value(ShowConstants.DB_GENRE))
		.andExpect(jsonPath("$.[*].show.type").value(ShowConstants.DB_TYPE))
		.andExpect(jsonPath("$.[*].show.description").value(ShowConstants.DB_DESCRIPTION))
		.andExpect(jsonPath("$.[*].show.cast").value(ShowConstants.DB_CAST))
		.andExpect(jsonPath("$.[*].show.length").value(ShowConstants.DB_LENGTH))
		.andExpect(jsonPath("$.[*].rating").value(ShowConstants.DB_RATING));
	}

	@Test
	public void testGetShow() throws Exception{
		this.mockMvc.perform(get(URL_PREFIX + "/" + ShowConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].types").value(ShowConstants.DB_TYPES))
		.andExpect(jsonPath("$.[*].genres").value(ShowConstants.DB_GENRES))
		.andExpect(jsonPath("$.[*].show").value(ShowConstants.DB_SHOW));
	}


	@Test
	public void testAddShow() throws Exception{
		Show show = new Show();
		show.setCast("asd");
		show.setDescription("asd");
		show.setGenre(new Genre());
		show.setLength(1);
		show.setType(new ShowType());
		show.setId(ShowConstants.DB_ID);
		String json = TestUtil.json(show);
		this.mockMvc.perform(post(URL_PREFIX).content(json)).andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception{
		this.mockMvc.perform(delete(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk());
	}

}
