package com.example.stayease.dto.roomDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomRequest {

    @NotBlank(message = "Otaq tipi boş ola bilməz")
    private String type;

    @NotNull
    @NotBlank(message = "Qiymet musbet olmalidir")
    private Double pricePerNight;

    @NotNull
    @Min(1)
    private Integer capacity;

    @NotNull
    @Min(1)
    private Integer totalRooms;

    @NotNull(message = "Hotel Id bos ola bilmez")
    private Long hotelId;


}
