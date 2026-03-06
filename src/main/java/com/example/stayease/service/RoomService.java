package com.example.stayease.service;

import com.example.stayease.dto.roomDto.RoomRequest;
import com.example.stayease.dto.roomDto.RoomResponse;
import com.example.stayease.entity.Hotel;
import com.example.stayease.entity.Room;
import com.example.stayease.mapper.RoomMapper;
import com.example.stayease.repository.HotelRepository;
import com.example.stayease.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final HotelRepository hotelRepository;

    public RoomResponse create (RoomRequest request){
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(()->new RuntimeException("Hotel tapilmadi: " + request.getHotelId()));

        Room room = roomMapper.toEntity(request);
        room.setHotel(hotel);

        Room saved = roomRepository.save(room);
        return roomMapper.toResponse(saved);
    }

    public List<RoomResponse> getAll(){
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }
    public RoomResponse getById(Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Otaq tapilmadi: " + id));
        return roomMapper.toResponse(room);
    }
    public List<RoomResponse> getByHotelId(Long hotelId){
         return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }
    public RoomResponse update(Long id,RoomRequest request){
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Otaq tapilmadi: " + id));
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(()->new RuntimeException("Hotel tapilmadi: " + request.getHotelId()));
        room.setType(request.getType());
        room.setPricePerNight(request.getPricePerNight());
        room.setCapacity(request.getCapacity());
        room.setTotalRooms(request.getTotalRooms());
        room.setHotel(hotel);

        Room updated = roomRepository.save(room);
        return roomMapper.toResponse(updated);
    }

    public void delete(Long id){
        if(!roomRepository.existsById(id)){
            throw new RuntimeException("Otaq tapilmadi: " + id);
        }
        roomRepository.deleteById(id);
    }
}
