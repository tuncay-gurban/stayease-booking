package com.example.stayease.dto.roomDto;

import lombok.Data;

@Data
public class RoomResponse {
    private Long id;
    private String type;
    private Double pricePerNight;
    private Integer capacity;
    private Integer totalRooms;
    private Long hotelId;
    private String hotelName;
}
