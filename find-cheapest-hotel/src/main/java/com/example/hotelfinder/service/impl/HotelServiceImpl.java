package com.example.hotelfinder.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.hotelfinder.dto.CheapestHotelResponse;
import com.example.hotelfinder.entity.Hotel;
import com.example.hotelfinder.enums.CustomerType;
import com.example.hotelfinder.exception.HotelNotFoundException;
import com.example.hotelfinder.repository.HotelRepository;
import com.example.hotelfinder.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	private final HotelRepository hotelRepository;

	public HotelServiceImpl(HotelRepository hotelRepository) {

		this.hotelRepository = hotelRepository;
	}
 
	public CheapestHotelResponse findCheapestHotel(CustomerType customerType, LocalDate startDate,LocalDate endDate) {
		
		List<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
		Hotel cheapestHotel = hotelRepository.findAll().stream()
				.min(Comparator.<Hotel>comparingDouble(hotel -> calculateTotalCost(hotel, customerType, dates))
						.thenComparing(Comparator.comparingInt(Hotel::getRating).reversed()))
				.orElseThrow(() -> new HotelNotFoundException("No hotels available for the given criteria"));
		double totalCost = calculateTotalCost(cheapestHotel, customerType, dates);
		return new CheapestHotelResponse(cheapestHotel.getName(),totalCost);
	}

	
	
	private double calculateTotalCost(Hotel hotel, CustomerType customerType, List<LocalDate> dates) {
		double totalCost = dates.stream().mapToDouble(date -> hotel.getRate(customerType, isWeekend(date))).sum();
	
		return totalCost;
	}

	private boolean isWeekend(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

}
