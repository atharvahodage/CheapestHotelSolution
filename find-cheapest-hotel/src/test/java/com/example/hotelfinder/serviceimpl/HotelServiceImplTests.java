package com.example.hotelfinder.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.hotelfinder.entity.Hotel;
import com.example.hotelfinder.enums.CustomerType;
import com.example.hotelfinder.repository.HotelRepository;
import com.example.hotelfinder.service.impl.HotelServiceImpl;

public class HotelServiceImplTests {


    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveHotel() {
        Hotel hotel = new Hotel(1L, "Miami Beach", 5, 100, 150, 80, 120);
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        Hotel savedHotel = hotelService.saveHotel(hotel);

        verify(hotelRepository).save(hotel);
        assertEquals(hotel, savedHotel);
    }
    
    
    @Test
    void testFindCheapestHotel() {
        List<LocalDate> dates = Arrays.asList(LocalDate.of(2024, 7, 8));
        Hotel hotel1 = new Hotel(1L, "Miami Beach", 4, 80, 110, 50, 90);
        Hotel hotel2 = new Hotel(2L, "Miami Downtown", 3, 120, 90, 80, 100);
        Hotel hotel3 = new Hotel(2L, "Miami Midtown", 5, 100, 150, 70, 130);
        List<Hotel> hotels = Arrays.asList(hotel1, hotel2,hotel3);

        when(hotelRepository.findAll()).thenReturn(hotels);

        Hotel cheapestHotel = hotelService.findCheapestHotel(CustomerType.REGULAR, dates);

        assertEquals(hotel1, cheapestHotel);
    }

}
