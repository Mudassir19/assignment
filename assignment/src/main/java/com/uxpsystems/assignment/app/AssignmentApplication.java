package com.uxpsystems.assignment.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.uxpsystems.assignment.app.model.AssignmentEntities;
import com.uxpsystems.assignment.app.service.AssignmentService;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(AssignmentApplication.class, args);

		AssignmentService assignmentService = applicationContext.getBean("assignmentService", AssignmentService.class);

		AssignmentEntities entities = new AssignmentEntities();

		entities.setStatus("Deactivated");
		entities.setUserName("Ramesh");
		entities.setPassword("abc@123");
		assignmentService.createRecords(entities);

	}

}
