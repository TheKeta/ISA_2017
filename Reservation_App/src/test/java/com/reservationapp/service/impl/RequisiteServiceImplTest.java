package com.reservationapp.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Requisite;

import test.constants.RequisiteConstants;

class RequisiteServiceImplTest {
	@Autowired
	private RequisiteServiceImpl reqService;

	@Test
	void testFindOne() {
		Requisite req = reqService.findOne(RequisiteConstants.DB_ID);
		assertThat(req).isNotNull();
		assertThat(req.getId()).isEqualTo(RequisiteConstants.DB_ID);
		assertThat(req.getCreator()).isEqualTo(RequisiteConstants.DB_CREATOR);
        assertThat(req.getDescription()).isEqualTo(RequisiteConstants.DB_DESCRIPTION);
        assertThat(req.getEndDate()).isEqualTo(RequisiteConstants.DB_ENDDATE);  
        assertThat(req.getName()).isEqualTo(RequisiteConstants.DB_NAME); 
        assertThat(req.getPrice()).isEqualTo(RequisiteConstants.DB_PRICE); 
        assertThat(req.getType()).isEqualTo(RequisiteConstants.DB_TYPE); 
	}


	@Test
	void testFindAll() {
		List<Requisite> req = reqService.findAll();
		assertThat(req).hasSize(2);
	}


	@Test
	@Transactional
    @Rollback(true) 
	void testSaveRequisite() {
		Requisite req = new Requisite();
		req.setActive(true);
		req.setApproved(false);
		req.setCreator(RequisiteConstants.DB_CREATOR);
		req.setDescription(RequisiteConstants.DB_DESCRIPTION);
		req.setEndDate(RequisiteConstants.DB_ENDDATE);
		req.setName(RequisiteConstants.DB_NAME);
		req.setPictureDB(RequisiteConstants.DB_PICTUREDB);
		req.setPrice(RequisiteConstants.DB_PRICE);
		req.setType(RequisiteConstants.DB_TYPE);
		
		int dbSizeBeforeAdd = reqService.findAll().size();
		
		Requisite re = reqService.save(req);
		assertThat(re).isNotNull();
		
		List<Requisite> reqs = reqService.findAll();
		assertThat(reqs).hasSize(dbSizeBeforeAdd + 1);
	    re = reqs.get(reqs.size() - 1);
	    
	    assertThat(re.getId()).isEqualTo(RequisiteConstants.DB_ID);
		assertThat(re.getCreator()).isEqualTo(RequisiteConstants.DB_CREATOR);
        assertThat(re.getDescription()).isEqualTo(RequisiteConstants.DB_DESCRIPTION);
        assertThat(re.getEndDate()).isEqualTo(RequisiteConstants.DB_ENDDATE);  
        assertThat(re.getName()).isEqualTo(RequisiteConstants.DB_NAME); 
        assertThat(re.getPrice()).isEqualTo(RequisiteConstants.DB_PRICE); 
        assertThat(re.getType()).isEqualTo(RequisiteConstants.DB_TYPE); 
	}

	
	@Test
	@Transactional
	@Rollback(true)
	void testDeleteLong() {
		int dbSizeBeforeRemove = reqService.findAll().size();
		reqService.delete(1L);
		
		List<Requisite> students = reqService.findAll();
		assertThat(students).hasSize(dbSizeBeforeRemove - 1);
		
		Requisite dbStudent = reqService.findOne(1L);
		assertThat(dbStudent).isNull();
		
	}


	@Test
	void testFindAllUserReqs() {
		List<Requisite> req = reqService.findAllUserReqs(RequisiteConstants.DB_TYPE);
		assertThat(req).hasSize(2);
	}

	@Test
	void testUpdate() {
		Requisite req = reqService.findOne(RequisiteConstants.DB_ID);
		req.setCreator(RequisiteConstants.NEW_CREATOR);
		req.setDescription(RequisiteConstants.NEW_DESCRIPTION);
		req.setEndDate(RequisiteConstants.NEW_ENDDATE);
		req.setName(RequisiteConstants.NEW_NAME);
		req.setPictureDB(RequisiteConstants.NEW_PICTUREDB);
		req.setPrice(RequisiteConstants.NEW_PRICE);
		req.setType(RequisiteConstants.NEW_TYPE);
		
		req = reqService.update(req);
		assertThat(req).isNotNull();
		
		req = reqService.findOne(RequisiteConstants.DB_ID);
		 assertThat(req.getId()).isEqualTo(RequisiteConstants.DB_ID);
			assertThat(req.getCreator()).isEqualTo(RequisiteConstants.NEW_CREATOR);
	        assertThat(req.getDescription()).isEqualTo(RequisiteConstants.NEW_DESCRIPTION);
	        assertThat(req.getEndDate()).isEqualTo(RequisiteConstants.NEW_ENDDATE);  
	        assertThat(req.getName()).isEqualTo(RequisiteConstants.NEW_NAME); 
	        assertThat(req.getPrice()).isEqualTo(RequisiteConstants.NEW_PRICE); 
	        assertThat(req.getType()).isEqualTo(RequisiteConstants.NEW_TYPE); 
	}

	@Test
	void testFindByEndDateLessThanAndIsActiveTrue() {
		List<Requisite> req = reqService.findByEndDateLessThanAndIsActiveTrue(RequisiteConstants.DB_ENDDATE);
		assertThat(req).hasSize(2);
	}

	@Test
	void testFindAllUserFreshReqs() {
		List<Requisite> req = reqService.findAllUserFreshReqs();
		assertThat(req).hasSize(2);
	}

}
