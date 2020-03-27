package com.uxpsystems.assignment.app.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.uxpsystems.assignment.app.model.AssignmentEntities;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssignmentDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AssignmentDao assignmentDao;

	@Test
	public void testSaveRecords() {

		AssignmentEntities entities = getRecords();
		AssignmentEntities saveInDb = entityManager.persist(entities);

		AssignmentEntities getFromDb = assignmentDao.findById(saveInDb.getId()).orElse(null);

		assertThat(getFromDb).isEqualTo(saveInDb);

	}

	private AssignmentEntities getRecords() {
		AssignmentEntities entities = new AssignmentEntities();

		entities.setUserName("john");
		entities.setPassword("john@123");
		entities.setStatus("activated");

		return entities;
	}

	@Test
	public void testRecordsById() {
		AssignmentEntities entities = new AssignmentEntities();

		entities.setUserName("Mike");
		entities.setPassword("Mike@123");
		entities.setStatus("Deactivated");
		
		// Save record in DB
		AssignmentEntities savedInDb = entityManager.persist(entities);

		// Get record from DB
		AssignmentEntities FromInDb = assignmentDao.findById(savedInDb.getId()).orElse(null);

		assertThat(savedInDb).isEqualTo(FromInDb);
	}

	@Test
	public void testGetAllRecords() {
		AssignmentEntities record1 = new AssignmentEntities();
		record1.setUserName("Martin");
		record1.setPassword("martin@987");
		record1.setStatus("activated");

		AssignmentEntities record2 = new AssignmentEntities();
		record2.setUserName("Guptil");
		record2.setPassword("Guptil@987");
		record2.setStatus("Deactivated");

		// Save both records in DB
		entityManager.persist(record1);
		entityManager.persist(record2);

		Iterable<AssignmentEntities> allRecordsFromDb = assignmentDao.findAll();
		List<AssignmentEntities> recordList = new ArrayList<>();

		for (AssignmentEntities record : allRecordsFromDb) {
			recordList.add(record);
		}
		assertThat(recordList.size()).isEqualTo(2);
	}

	@Test
	public void testDeleteRecordById() {
		AssignmentEntities record1 = new AssignmentEntities();
		record1.setUserName("Martin");
		record1.setPassword("martin@987");
		record1.setStatus("activated");

		AssignmentEntities record2 = new AssignmentEntities();
		record2.setUserName("Guptil");
		record2.setPassword("Guptil@987");
		record2.setStatus("Deactivated");

		// Save both records in DB
		AssignmentEntities persist = entityManager.persist(record1);
		entityManager.persist(record2);

		// delete one record from DB
		entityManager.remove(persist);

		Iterable<AssignmentEntities> allRecordsFromDb = assignmentDao.findAll();
		List<AssignmentEntities> recordList = new ArrayList<>();

		for (AssignmentEntities record : allRecordsFromDb) {
			recordList.add(record);
		}
		assertThat(recordList.size()).isEqualTo(1);
	}
	
	@Test
	public void testUpdateRecord(){
		AssignmentEntities record = new AssignmentEntities();
		
		
		record.setUserName("Martin");
		record.setPassword("martin@987");
		record.setStatus("activated");
		
		//save record info in DB
		entityManager.persist(record);
		
		AssignmentEntities entities = assignmentDao.findById(record.getId()).get();
		
		entities.setStatus("deactivated");
		AssignmentEntities upadedRecords = assignmentDao.save(entities);
		
		entityManager.persist(upadedRecords);
		
		assertThat(upadedRecords.getStatus()).isEqualTo("deactivated");
	}

}
