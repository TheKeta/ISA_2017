package com.reservationapp.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Requisite;

import main.TestUtil;
import test.constants.RequisiteConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
class RequisiteControllerTest {

private static final String URL_PREFIX = "/requisite";
	

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	
	private MockMvc mockMvc;

	
	

	@Test
	void testGetRequisitesF() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getFreshRequisites")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(RequisiteConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].creator").value(hasItem(RequisiteConstants.DB_CREATOR.intValue())))
		.andExpect(jsonPath("$.[*].name").value(hasItem(RequisiteConstants.DB_NAME)))
		.andExpect(jsonPath("$.[*].type").value(hasItem(RequisiteConstants.DB_TYPE)))
		.andExpect(jsonPath("$.[*].endDate").value(hasItem(RequisiteConstants.DB_ENDDATE)))
		.andExpect(jsonPath("$.[*].price").value(hasItem(RequisiteConstants.DB_PRICE)))
		.andExpect(jsonPath("$.[*].description").value(hasItem(RequisiteConstants.DB_DESCRIPTION)));
	}

	@Test
	void testGetRequisitesU()  throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/getRequisites/"+RequisiteConstants.NEW_TYPE)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(RequisiteConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].creator").value(hasItem(RequisiteConstants.DB_CREATOR.intValue())))
		.andExpect(jsonPath("$.[*].name").value(hasItem(RequisiteConstants.DB_NAME)))
		.andExpect(jsonPath("$.[*].type").value(hasItem(RequisiteConstants.DB_TYPE)))
		.andExpect(jsonPath("$.[*].endDate").value(hasItem(RequisiteConstants.DB_ENDDATE)))
		.andExpect(jsonPath("$.[*].price").value(hasItem(RequisiteConstants.DB_PRICE)))
		.andExpect(jsonPath("$.[*].description").value(hasItem(RequisiteConstants.DB_DESCRIPTION)));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testupdateA() throws Exception {
		Requisite req = new Requisite();
		req.setId(RequisiteConstants.DB_ID);
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		String json = TestUtil.json(req);
		this.mockMvc.perform(post(URL_PREFIX + "/approve/"+RequisiteConstants.DB_ID).content(json)).andExpect(status().isOk());
		
	}

	@Test
	@Transactional
	@Rollback(true)
	void testUpdateD() throws Exception {
		Requisite req = new Requisite();
		req.setId(RequisiteConstants.DB_ID);
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		String json = TestUtil.json(req);
		this.mockMvc.perform(put(URL_PREFIX + "/disprove/"+RequisiteConstants.DB_ID).content(json)).andExpect(status().isOk());
		
	}

	@Test
	void testGetRequisite() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/addNewReq"+RequisiteConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(RequisiteConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].creator").value(hasItem(RequisiteConstants.DB_CREATOR.intValue())))
		.andExpect(jsonPath("$.[*].name").value(hasItem(RequisiteConstants.DB_NAME)))
		.andExpect(jsonPath("$.[*].type").value(hasItem(RequisiteConstants.DB_TYPE)))
		.andExpect(jsonPath("$.[*].endDate").value(hasItem(RequisiteConstants.DB_ENDDATE)))
		.andExpect(jsonPath("$.[*].price").value(hasItem(RequisiteConstants.DB_PRICE)))
		.andExpect(jsonPath("$.[*].description").value(hasItem(RequisiteConstants.DB_DESCRIPTION)));
	}

	@Transactional
	@Rollback(true)
	@Test
	void testAddRequisite() throws Exception {
		Requisite req = new Requisite();
		req.setId(RequisiteConstants.DB_ID);
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		String json = TestUtil.json(req);
		this.mockMvc.perform(post(URL_PREFIX + "/addNewReq/").content(json)).andExpect(status().isOk());
		
	}

	@Test
	@Transactional
	@Rollback(true)
	void testAddRequisitee() throws Exception {
		Requisite req = new Requisite();
		req.setId(RequisiteConstants.DB_ID);
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		String json = TestUtil.json(req);
		this.mockMvc.perform(post(URL_PREFIX + "/addNewReqq/").content(json)).andExpect(status().isOk());
	}

	@Test
	@Transactional
	@Rollback(true)
	void testEditRequisitee() throws Exception {
		Requisite req = new Requisite();
		req.setId(RequisiteConstants.DB_ID);
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		String json = TestUtil.json(req);
		this.mockMvc.perform(post(URL_PREFIX + "/editReqq/").content(json)).andExpect(status().isOk());
	}

	@Test
	@Transactional
	@Rollback(true)
	void testEditRequisite()throws Exception {
		Requisite req = new Requisite();
		req.setId(RequisiteConstants.DB_ID);
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		String json = TestUtil.json(req);
		this.mockMvc.perform(post(URL_PREFIX + "/editReq/").content(json)).andExpect(status().isOk());
	}

	@Test
	@Transactional
	@Rollback(true)
	void testDelete() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + RequisiteConstants.DB_ID)).andExpect(status().isOk());

	}

}
