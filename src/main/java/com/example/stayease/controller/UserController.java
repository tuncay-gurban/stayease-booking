package com.example.stayease.controller;

import com.example.stayease.dto.userDto.UserRequest;
import com.example.stayease.dto.userDto.UserResponse;
import com.example.stayease.entity.User;
import com.example.stayease.mapper.UserMapper;
import com.example.stayease.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        User user = userMapper.toEntity(request);
        User saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toResponse(saved));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> users = userService.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
