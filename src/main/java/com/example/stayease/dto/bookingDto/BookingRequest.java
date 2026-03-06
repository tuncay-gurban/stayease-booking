package com.example.stayease.dto.bookingDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class BookingRequest {

    @NotNull(message = "User ID bos ola bilmez")
    private Long userId;

    @NotNull(message = "Room ID bos ola bilmez")
    private Long roomId;

    @NotNull(message = "Check-in tarixi bos ola bilmez")
    @FutureOrPresent(message = "Chekc-in tarixi kecmisde ola bilmez")
    private LocalDate checkIn;

    @Future(message = "Check-out tarixi gelecekde olmalidir")
    @NotNull(message = "Chek-out tarixi bos ola bilmez")
    private LocalDate checkOut;

}
