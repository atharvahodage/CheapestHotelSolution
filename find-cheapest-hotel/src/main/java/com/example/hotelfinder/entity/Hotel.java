package com.example.hotelfinder.entity;


import java.util.Objects;

import com.example.hotelfinder.enums.CustomerType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private final String name;
	private final int rating;
	private final double regularWeekdayRate;
	private final double regularWeekendRate;
	private final double rewardWeekdayRate;
	private final double rewardWeekendRate;


	public double getRate(CustomerType customerType, boolean isWeekend) {
		if (customerType == CustomerType.REGULAR) {
			return isWeekend ? regularWeekendRate : regularWeekdayRate;
		} else {
			return isWeekend ? rewardWeekendRate : rewardWeekdayRate;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Hotel))
			return false;
		Hotel hotel = (Hotel) o;
		return Objects.equals(name, hotel.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	
	
	

	public Hotel() {
		this.name = null;
		this.rating = 0;
		this.regularWeekdayRate = 0.0;
		this.regularWeekendRate = 0.0;
		this.rewardWeekdayRate = 0.0;
		this.rewardWeekendRate = 0.0;
	}
}