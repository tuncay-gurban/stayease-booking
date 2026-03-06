package com.example.stayease.repository;

import com.example.stayease.entity.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByCity(String city);
    List<Hotel> findByStarRatingGreaterThanEqual(Integer starRating);
    List<Hotel> findByCityAndStarRatingGreaterThanEqual(Integer starRating,String city);
}
