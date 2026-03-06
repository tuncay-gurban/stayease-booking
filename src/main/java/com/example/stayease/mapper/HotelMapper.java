package com.example.stayease.mapper;

import com.example.stayease.dto.hotelDto.HotelRequest;
import com.example.stayease.dto.hotelDto.HotelResponse;
import com.example.stayease.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    Hotel toEntity(HotelRequest request);
    HotelResponse toResponse(Hotel hotel);
}
