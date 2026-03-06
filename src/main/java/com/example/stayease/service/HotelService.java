package com.example.stayease.service;

import com.example.stayease.dto.hotelDto.HotelRequest;
import com.example.stayease.dto.hotelDto.HotelResponse;
import com.example.stayease.entity.Hotel;
import com.example.stayease.mapper.HotelMapper;
import com.example.stayease.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    public HotelResponse create(HotelRequest request){
        Hotel hotel = hotelMapper.toEntity(request);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toResponse(saved);
    }

    public List<HotelResponse>getAll(){
        return hotelRepository.findAll()
                .stream()
                .map(hotelMapper::toResponse)
                .collect(Collectors.toList());
    }

    public HotelResponse getById(Long id){
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("Hotel tapilmadi: " + id));
        return hotelMapper.toResponse(hotel);
    }

    public List<HotelResponse> getByCity(String city){
        return hotelRepository.findByCity(city)
                .stream()
                .map(hotelMapper::toResponse)
                .collect(Collectors.toList());
    }

    public HotelResponse update(Long id, HotelRequest request){
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Hotel tapilmadi: " + id));

        hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setAddress(request.getAddress());
        hotel.setStarRating(request.getStarRating());
        hotel.setDescription(request.getDescription());
        hotel.setImgUrl(request.getImageUrl());

        Hotel updated = hotelRepository.save(hotel);
        return hotelMapper.toResponse(updated);

    }

    public void delete(Long id){
        if(!hotelRepository.existsById(id)){
            throw new RuntimeException("Hotel tapilmadi: " + id);
        }
        hotelRepository.deleteById(id);
    }
}
