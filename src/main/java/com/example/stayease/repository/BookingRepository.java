package com.example.stayease.repository;

import com.example.stayease.entity.Booking;
import com.example.stayease.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUseriD(Long userId);
    List<Booking> findByRoomId(Long roomId);
    List<Booking> findByStatusId(BookingStatus status);
    List<Booking> findByRoomAndStatusNotAndCheckOutAfterAndCheckInBefore(
            Long roomId, BookingStatus status, LocalDate checkIn,LocalDate checkOut
            );

}
