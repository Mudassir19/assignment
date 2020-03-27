package com.uxpsystems.assignment.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.assignment.app.model.AssignmentEntities;

@Repository
public interface AssignmentDao extends CrudRepository<AssignmentEntities, Long> {

}
