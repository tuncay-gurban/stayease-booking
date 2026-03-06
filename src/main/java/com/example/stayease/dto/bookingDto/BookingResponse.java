package com.example.stayease.dto.bookingDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookingResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long roomId;
    private String roomType;
    private String hotelName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;
}
