package com.example.stayease.service;

import com.example.stayease.dto.bookingDto.BookingRequest;
import com.example.stayease.dto.bookingDto.BookingResponse;
import com.example.stayease.entity.Booking;
import com.example.stayease.entity.BookingStatus;
import com.example.stayease.entity.Room;
import com.example.stayease.entity.User;
import com.example.stayease.mapper.BookingMapper;
import com.example.stayease.mapper.RoomMapper;
import com.example.stayease.mapper.UserMapper;
import com.example.stayease.repository.BookingRepository;
import com.example.stayease.repository.RoomRepository;
import com.example.stayease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;
    private final RoomMapper roomMapper;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingResponse create(BookingRequest request){
        // 1. Tarix validasiyasi
        if(!request.getCheckOut().isAfter(request.getCheckIn())){
            throw new RuntimeException("Check-out tarixi Check-in tarixinden sonra olmalidir");
        }
        // 2.User ve Room tap
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("Istifadeci tapilmadi" + request.getUserId()));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(()->new RuntimeException("Otaq tapilmadi: " + request.getRoomId()));

        // 3.Availabilty check
        List<Booking> overlapping = bookingRepository
                .findByRoomAndStatusNotAndCheckOutAfterAndCheckInBefore(
                        room.getId(), BookingStatus.CANCELLED, request.getCheckIn(),request.getCheckOut());
         if(overlapping.size() >= room.getTotalRooms()){
             throw  new RuntimeException("Bu tarixlerde musait otaq yoxdur");
         }

         // 4.Qiymet hesabla

        long nigths = ChronoUnit.DAYS.between(request.getCheckIn(),request.getCheckOut());
         double totalPrice = nigths * room.getPricePerNight();

         // 5.Booking yarat
        Booking booking = Booking.builder()
                .user(user)
                .room(room)
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .totalPrice(totalPrice)
                .status(BookingStatus.PENDING)
                .build();
        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toResponse(saved);
    }

    public List<BookingResponse> getAll(){
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }
    public BookingResponse getById(Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Rezervasiya tapilmadi: " + id));
        return bookingMapper.toResponse(booking);
    }

    public List<BookingResponse> getByUserId(Long userId){
        return bookingRepository.findByUseriD(userId)
                .stream()
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }

}
