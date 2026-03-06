package com.example.stayease.dto.hotelDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class HotelRequest {

    @NotBlank(message = "Hotel adı boş ola bilməz")
    private String name;

    @NotBlank(message = "Şəhər boş ola bilməz")
    private String city;

    @NotBlank(message = "Ünvan boş ola bilməz")
    private String address;

    @NotNull
    @Min(1) @Max(5)
    private Integer starRating;

    private String description;

    private String imageUrl;

}
