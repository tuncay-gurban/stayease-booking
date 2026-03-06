package com.example.stayease.mapper;

import com.example.stayease.dto.bookingDto.BookingResponse;
import com.example.stayease.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.firstName", target = "userName")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.type", target = "roomType")
    @Mapping(source = "room.hotel.name", target = "hotelName")
    BookingResponse toResponse(Booking booking);
}
