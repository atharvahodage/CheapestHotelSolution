package com.example.hotelfinder.service;

import java.time.LocalDate;
import java.util.List;

import com.example.hotelfinder.dto.CheapestHotelResponse;
import com.example.hotelfinder.entity.Hotel;
import com.example.hotelfinder.enums.CustomerType;

public interface HotelService {
	CheapestHotelResponse findCheapestHotel(CustomerType customerType, LocalDate startDate,LocalDate endDate);

	Hotel saveHotel(Hotel hotel);

	List<Hotel> getAllHotels();

}
