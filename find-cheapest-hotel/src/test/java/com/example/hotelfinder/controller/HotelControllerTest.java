package com.example.hotelfinder.controller;

import com.example.hotelfinder.entity.Hotel;
import com.example.hotelfinder.enums.CustomerType;
import com.example.hotelfinder.service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HotelService hotelService;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		objectMapper = new ObjectMapper();
	}

	@Test
	void testAddHotel() throws Exception {
		Hotel hotel = new Hotel(1L, "Miami Beach", 5, 100, 150, 80, 120);
		when(hotelService.saveHotel(any(Hotel.class))).thenReturn(hotel);

		mockMvc.perform(post("/hotels/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(hotel))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Miami Beach"));
	}

	@Test
	void testFindCheapestHotel() throws Exception {
		    Hotel hotel1 = new Hotel(1L, "Miami Beach", 4, 80, 110, 50, 90);
	        Hotel hotel2 = new Hotel(2L, "Miami Downtown", 3, 120, 90, 100, 80);
	        Hotel hotel3 = new Hotel(2L, "Miami Midtown", 5, 100, 150, 70, 130);
	       
		  
		    LocalDate date1 = LocalDate.of(2024, 7, 7);
		    LocalDate date2 = LocalDate.of(2024, 7, 8);
		    List<LocalDate> dates = Arrays.asList(date1,date2);

		 
		    when(hotelService.findCheapestHotel(eq(CustomerType.REWARD), eq(dates)))
		            .thenReturn(hotel2);

		
		    mockMvc.perform(get("/hotels/findCheapestHotel")
		            .param("customerType", "REWARD")
		            .param("dates", "2024-07-07,2024-07-08"))
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$.name").value("Miami Downtown")); 

	}
}
