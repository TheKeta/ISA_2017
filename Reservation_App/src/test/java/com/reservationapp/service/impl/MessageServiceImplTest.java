package com.reservationapp.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Message;

import test.constants.MessageConstants;

class MessageServiceImplTest {
	
	@Autowired
	private MessageServiceImpl msgs;

	@Test
	void testFindOne() {
		Message req = msgs.findOne(MessageConstants.DB_ID);
		assertThat(req).isNotNull();
		assertThat(req.getId()).isEqualTo(MessageConstants.DB_ID);
		assertThat(req.getReciverID()).isEqualTo(MessageConstants.DB_RECIVERID);
        assertThat(req.getSenderID()).isEqualTo(MessageConstants.DB_SENDERID);
        assertThat(req.getText()).isEqualTo(MessageConstants.DB_TEXT);  
        assertThat(req.isRead()).isEqualTo(MessageConstants.DB_READ); 
        
	}

	@Test
	void testFindAll() {
		List<Message> req = msgs.findAll();
		assertThat(req).hasSize(2);
	}

	@Test
	@Transactional
	@Rollback(true)
	void testSaveMessage() {
		Message req = new Message();
		req.setRead(MessageConstants.DB_READ);
		req.setReciverID(MessageConstants.DB_RECIVERID);
		req.setSenderID(MessageConstants.DB_SENDERID);
		req.setText(MessageConstants.DB_TEXT);
		
		int dbSizeBeforeAdd = msgs.findAll().size();
		
		Message re = msgs.save(req);
		assertThat(re).isNotNull();
		
		List<Message> reqs = msgs.findAll();
		assertThat(reqs).hasSize(dbSizeBeforeAdd + 1);
	    re = reqs.get(reqs.size() - 1);
	    
	    assertThat(re.getId()).isEqualTo(MessageConstants.DB_ID);
		assertThat(re.getReciverID()).isEqualTo(MessageConstants.DB_RECIVERID);
        assertThat(re.getSenderID()).isEqualTo(MessageConstants.DB_SENDERID);  
        assertThat(re.getText()).isEqualTo(MessageConstants.DB_TEXT); 
        assertThat(re.isRead()).isEqualTo(MessageConstants.DB_READ); 
	}

	

	@Test
	@Transactional
	@Rollback(true)
	void testDeleteLong() {
		int dbSizeBeforeRemove = msgs.findAll().size();
		msgs.delete(1L);
		
		List<Message> students = msgs.findAll();
		assertThat(students).hasSize(dbSizeBeforeRemove - 1);
		
		Message dbStudent = msgs.findOne(1L);
		assertThat(dbStudent).isNull();
		
	}

	
	@Test
	void testFindRMSG() {
		List<Message> req = msgs.findAll();
		assertThat(req).hasSize(2);
	}

	@Test
	void testFindSMSG() {
		List<Message> req = msgs.findAll();
		assertThat(req).hasSize(2);
	}

}
