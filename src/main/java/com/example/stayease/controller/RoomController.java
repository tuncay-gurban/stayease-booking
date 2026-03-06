package com.example.stayease.controller;

import com.example.stayease.dto.roomDto.RoomRequest;
import com.example.stayease.dto.roomDto.RoomResponse;
import com.example.stayease.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.create(request));
    }
    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAll(){
        return ResponseEntity.ok(roomService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getById(Long id){
        return ResponseEntity.ok(roomService.getById(id));
    }
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> getByHotelId(@PathVariable Long hotelId){
        return ResponseEntity.ok(roomService.getByHotelId(hotelId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(@PathVariable Long id,@Valid
                                               @RequestBody RoomRequest request){
        return ResponseEntity.ok(roomService.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
