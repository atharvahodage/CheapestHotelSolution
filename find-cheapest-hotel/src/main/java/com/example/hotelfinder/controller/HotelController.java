package com.example.hotelfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.hotelfinder.dto.CheapestHotelResponse;
import com.example.hotelfinder.entity.Hotel;
import com.example.hotelfinder.enums.CustomerType;
import com.example.hotelfinder.service.HotelService;


@RestController
@RequestMapping("/hotels")
public class HotelController {

	 
		
		private final HotelService hotelService;
	    
		@Autowired
		public HotelController(HotelService hotelService) {
	        this.hotelService = hotelService;
	    }

	    @GetMapping("/findCheapestHotel")
	    public CheapestHotelResponse findCheapestHotel(
	            @RequestParam CustomerType customerType,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
	            ) {
	        return hotelService.findCheapestHotel(customerType, startDate,endDate);
	    }
	    
	    @PostMapping("/add")
	    public Hotel addHotel(@RequestBody Hotel hotel) {
	        return hotelService.saveHotel(hotel);
	    }
	    
	    @GetMapping("/all")
	    public List<Hotel> getAllHotels() {
	        return hotelService.getAllHotels();
	    }
	    
	   

	    
}
