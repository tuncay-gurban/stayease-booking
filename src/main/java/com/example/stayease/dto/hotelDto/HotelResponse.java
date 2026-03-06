package com.example.stayease.dto.hotelDto;

import lombok.Data;

@Data
public class HotelResponse {

    private Long id;
    private String name;
    private String city;
    private String address;
    private Integer starRating;
    private String description;
    private String imageUrl;

}
