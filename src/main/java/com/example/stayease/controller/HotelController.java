package com.example.stayease.controller;

import com.example.stayease.dto.hotelDto.HotelRequest;
import com.example.stayease.dto.hotelDto.HotelResponse;
import com.example.stayease.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> create(@Valid @RequestBody HotelRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>>getAll(){
        return ResponseEntity.ok(hotelService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(hotelService.getById(id));
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<List<HotelResponse>> getByCity(@PathVariable String city){
        return ResponseEntity.ok(hotelService.getByCity(city));
    }
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> update(@PathVariable Long id,@Valid
                                                @RequestBody HotelRequest request){
        return ResponseEntity.ok(hotelService.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
