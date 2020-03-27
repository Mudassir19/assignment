package com.uxpsystems.assignment.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.app.model.AssignmentEntities;
import com.uxpsystems.assignment.app.service.AssignmentService; 

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AssignmentControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AssignmentService assignmentService;

	@Test
	@WithMockUser(username="mudassir",password="pass@123",roles="admin")
	public void testCreateRecord() throws Exception {

		AssignmentEntities mockRecord = new AssignmentEntities();

		mockRecord.setId(1L);
		mockRecord.setUserName("john");
		mockRecord.setStatus("Activated");
		mockRecord.setPassword("john@123");

		String inputInJson = this.mapToJson(mockRecord);

		String URI = "/assignement/records/create";

		Mockito.when(assignmentService.createRecords(Mockito.any(AssignmentEntities.class))).thenReturn(mockRecord);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	
	 @Test 
	 @WithMockUser(username="mudassir",password="pass@123",roles="admin")
	 public void testGetRecordById() throws Exception { 
	 AssignmentEntities mockRecord = new AssignmentEntities();
		 
		  mockRecord.setId(1L); 
		  mockRecord.setUserName("john");
		  mockRecord.setStatus("Activated"); 
		  mockRecord.setPassword("john@123");
		  Mockito.when(assignmentService.getRecordById(Mockito.anyLong())).thenReturn(Optional.of(mockRecord));
		  
		  String URI = "/assignement/records/get/1";
		  
		  RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		  
		  MvcResult result = mockMvc.perform(requestBuilder).andReturn(); 
		  
		  String expectedJson = this.mapToJson(mockRecord); 
		  String outputInJson =result.getResponse().getContentAsString();
		  assertThat(outputInJson).isEqualTo(expectedJson); 
		  }
	 
		@Test
		@WithMockUser(username="mudassir",password="pass@123",roles="admin")
		public void testAllRecords() throws Exception {

			AssignmentEntities record = new AssignmentEntities();

			record.setId(1001L);
			record.setUserName("john");
			record.setStatus("Activated");
			record.setPassword("john@123");

			AssignmentEntities record2 = new AssignmentEntities();

			record2.setId(1002L);
			record2.setUserName("Mike");
			record2.setStatus("Deactivated");
			record2.setPassword("mike@123");
			
			List<AssignmentEntities> recordList = new ArrayList<>();
			recordList.add(record);
			recordList.add(record2);
			
			Mockito.when(assignmentService.getAllRecords()).thenReturn(recordList);
			
			
			
			String URI = "/assignement/records/allrecords";
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			String expectedJson = this.mapToJson(recordList);
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isEqualTo(expectedJson);
		}

	
	/**
	  Maps an Object into a JSON String. Uses a Jackson ObjectMapper .
	*/
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	
	
}


