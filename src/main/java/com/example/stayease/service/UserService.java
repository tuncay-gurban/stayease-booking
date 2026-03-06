package com.example.stayease.service;

import com.example.stayease.entity.User;
import com.example.stayease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found with id:" + id));
    }

    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
