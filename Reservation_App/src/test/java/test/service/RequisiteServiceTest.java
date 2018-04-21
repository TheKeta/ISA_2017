package test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reservationapp.model.Requisite;
import com.reservationapp.service.RequisiteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequisiteServiceTest {

	@Autowired
	RequisiteService reqService;
	
	@Test
	public void testFindAll() {
		List<Requisite> students = reqService.findAll();
		assertThat(students).hasSize(12); 
	}
}
