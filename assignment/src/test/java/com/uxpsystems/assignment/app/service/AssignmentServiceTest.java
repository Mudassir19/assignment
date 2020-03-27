package com.uxpsystems.assignment.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uxpsystems.assignment.app.dao.AssignmentDao;
import com.uxpsystems.assignment.app.model.AssignmentEntities;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentServiceTest {

	@Autowired
	private AssignmentService assignmentService;

	@MockBean
	private AssignmentDao assignmentDao;

	@Test
	public void testCreateRecord() {

		AssignmentEntities record = new AssignmentEntities();

		record.setId(1L);
		record.setUserName("john");
		record.setStatus("Activated");
		record.setPassword("john@123");

		Mockito.when(assignmentDao.save(record)).thenReturn(record);

		assertThat(assignmentService.createRecords(record)).isEqualTo(record);

	}

	@Test
	public void testGetRecordsById() {
		AssignmentEntities record = new AssignmentEntities();

		record.setId(1L);
		record.setUserName("john");
		record.setStatus("Activated");
		record.setPassword("john@123");

		Mockito.when(assignmentDao.findById(record.getId())).thenReturn(Optional.of(record));
		assertThat(assignmentService.getRecordById(record.getId())).isEqualTo(Optional.of(record));
	}

	@Test
	public void testGetAllRecords() {
		AssignmentEntities record = new AssignmentEntities();

		record.setId(1L);
		record.setUserName("john");
		record.setStatus("Activated");
		record.setPassword("john@123");

		AssignmentEntities record2 = new AssignmentEntities();

		record.setId(2L);
		record2.setUserName("john");
		record2.setStatus("Activated");
		record2.setPassword("john@123");

		List<AssignmentEntities> recordList = new ArrayList<>();
		recordList.add(record);
		recordList.add(record2);

		Mockito.when(assignmentDao.findAll()).thenReturn(recordList);

		assertThat(assignmentService.getAllRecords()).isEqualTo(recordList);
	}

	@Test
	public void testDeleteRecord() {
		AssignmentEntities record = new AssignmentEntities();

		record.setId(1L);
		record.setUserName("john");
		record.setStatus("Activated");
		record.setPassword("john@123");

		Mockito.when(assignmentDao.findById(record.getId())).thenReturn(Optional.of(record));
		Mockito.when(assignmentDao.existsById(record.getId())).thenReturn(false);
		assertFalse(assignmentDao.existsById(record.getId()));
	}

	@Test
	public void testUpdateRecord() {
		AssignmentEntities record = new AssignmentEntities();
		
		record.setId(1L);
		record.setUserName("john");
		record.setStatus("Activated");
		record.setPassword("john@123");

		Mockito.when(assignmentDao.findById(record.getId())).thenReturn(Optional.of(record));

		record.setStatus("Deactivated");
		Mockito.when(assignmentDao.save(record)).thenReturn(record);

		assertThat(assignmentService.updateRecord(1, "Deactivated")).isEqualTo(record);

	}

}
