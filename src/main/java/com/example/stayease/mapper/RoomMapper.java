package com.example.stayease.mapper;

import com.example.stayease.dto.roomDto.RoomRequest;
import com.example.stayease.dto.roomDto.RoomResponse;
import com.example.stayease.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping (target = "hotel", ignore = true)
    Room toEntity(RoomRequest request);

    @Mapping(source = "hotel.id",target = "hotelId")
    @Mapping(source = "hotel.name",target = "hotelName")
    RoomResponse toResponse(Room room);

}
