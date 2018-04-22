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

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.reservationapp.model.Genre;

import main.TestUtil;
import test.constants.EventConstants;
import test.constants.GenreConstants;

public class GenreControllerTest {


	private static final String URL_PREFIX = "/genre";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	
	@Test
	public void testGetEvents() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getGenres")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(GenreConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].name").value(hasItem(GenreConstants.DB_NAME)));
		
	}

	@Test
	public void testGetGenre() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + GenreConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(GenreConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].name").value(hasItem(GenreConstants.DB_NAME)));
	}

	@Test
	public void testAddGenre() throws Exception {
		Genre genre = new Genre(GenreConstants.NEW_NAME);		
		String json = TestUtil.json(genre);
		this.mockMvc.perform(post(URL_PREFIX).content(json)).andExpect(status().isOk());
	}

	@Test
	public void testDelete() throws Exception{
		this.mockMvc.perform(delete(URL_PREFIX + "/" + EventConstants.DB_ID)).andExpect(status().isOk());
	}

}
