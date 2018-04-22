package com.reservationapp.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import test.constants.InstitutionTypeConstants;

public class InstitutionTypeControllerTest {
private static final String URL_PREFIX = "/institutionType";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	private MockMvc mockMvc;

	@Test
	public void testGetInstitutionTypes() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getInstitutionTypes/" + InstitutionTypeConstants.DB_NAME)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].institutionType.id").value(hasItem(InstitutionTypeConstants.DB_ID)))
		.andExpect(jsonPath("$.[*].institutionType.name").value(hasItem(InstitutionTypeConstants.DB_NAME)))
		.andExpect(jsonPath("$.[*].users").value(hasItem(InstitutionTypeConstants.DB_USERS)))
		;
	}

	@Test
	public void testGetInstitutionTypeId() throws Exception  {
		mockMvc.perform(get(URL_PREFIX + "/institutionTypeId/" +  InstitutionTypeConstants.DB_NAME)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(InstitutionTypeConstants.DB_ID)))
		.andExpect(jsonPath("$.[*].name").value(hasItem(InstitutionTypeConstants.DB_NAME)));
	}

	@Test
	public void testGetInstitution() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + InstitutionTypeConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.[*].id").value(hasItem(InstitutionTypeConstants.DB_ID)))
		.andExpect(jsonPath("$.[*].name").value(hasItem(InstitutionTypeConstants.DB_NAME)));
	}

}
