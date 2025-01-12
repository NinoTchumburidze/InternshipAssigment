package com.Assigment.assigment;

import com.Assigment.assigment.model.Transfer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import com.Assigment.assigment.service.service;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AssigmentApplicationTests {

	@Mock
	private service service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testProcessData1() throws Exception {
		// Expected JSON result after processing
		String mockResult = "{\"selectedTransfers\":[{\"weight\":10,\"cost\":20},{\"weight\":5,\"cost\":10}],\"totalCost\":30,\"totalWeight\":15}";


		Transfer transfer = new Transfer(15, 30);


		String inputJson = "{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":5,\"cost\":10},{\"weight\":10,\"cost\":20},{\"weight\":3,\"cost\":5},{\"weight\":8,\"cost\":15}]}";

		mockMvc.perform(post("/processData")
						.contentType(MediaType.APPLICATION_JSON)
						.content(inputJson))
				.andExpect(status().isOk())
				.andExpect(content().json(mockResult));
	}

	//Testing empty input
	@Test
	public void testProcessDataEmptyTransfers() throws Exception {
		// empty transfers
		String mockResult = "{\"selectedTransfers\":[],\"totalCost\":0,\"totalWeight\":0}";

		// Input JSON with an empty list
		String inputJson = "{\"maxWeight\":15,\"availableTransfers\":[]}";

		mockMvc.perform(post("/processData")
						.contentType(MediaType.APPLICATION_JSON)
						.content(inputJson))
				.andExpect(status().isOk())
				.andExpect(content().json(mockResult));
	}


	// When all transfers exceed the maxWeight limit
	@Test
	public void testProcessDataAllTransfersExceedMaxWeight() throws Exception {

		String mockResult = "{\"selectedTransfers\":[],\"totalCost\":0,\"totalWeight\":0}";


		String inputJson = "{\"maxWeight\":10,\"availableTransfers\":[{\"weight\":15,\"cost\":20},{\"weight\":12,\"cost\":25},{\"weight\":18,\"cost\":35}]}";

		mockMvc.perform(post("/processData")
						.contentType(MediaType.APPLICATION_JSON)
						.content(inputJson))
				.andExpect(status().isOk())
				.andExpect(content().json(mockResult));
	}






}
