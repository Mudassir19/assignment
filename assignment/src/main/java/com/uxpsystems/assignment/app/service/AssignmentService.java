package com.uxpsystems.assignment.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.app.dao.AssignmentDao;
import com.uxpsystems.assignment.app.model.AssignmentEntities;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentDao assignmentDao;

	public AssignmentEntities createRecords(AssignmentEntities entities) {
		return assignmentDao.save(entities);
	}

	public Optional<AssignmentEntities> getRecordById(long ticketId) {
		return assignmentDao.findById(ticketId);
	}

	public Iterable<AssignmentEntities> getAllRecords() {
		return assignmentDao.findAll();
	}

	public void deleteRecord(long id) {
		assignmentDao.deleteById(id);
	}

	public AssignmentEntities updateRecord(long id, String newStatus) {

		AssignmentEntities entities = assignmentDao.findById(id).get();
		entities.setStatus(newStatus);
		AssignmentEntities upadedRecords = assignmentDao.save(entities);
		return upadedRecords;
	}
}
