package com.peaksoft.springbootrestapi.controller;

import com.peaksoft.springbootrestapi.dto.request.UserRequest;
import com.peaksoft.springbootrestapi.dto.response.UserResponse;
import com.peaksoft.springbootrestapi.entity.User;
import com.peaksoft.springbootrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User saveUser(@RequestBody UserRequest user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUserById(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body("User with id: " + userId + " successfully deleted!");
    }

    @GetMapping
    public List<UserResponse> getAllUsers(@RequestParam Boolean isBlocked) {
        return userService.getAllUsers(isBlocked);
    }

    @PutMapping("/block/{userId}")
    public ResponseEntity blockUserOrUnblock(@PathVariable Long userId, @RequestParam(required = false) Boolean block) {
        userService.blockUserOrUnblock(userId, block);

        if (block) {
            return ResponseEntity.status(HttpStatus.OK).body("User with id: " + userId + " successfully blocked!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("User with id: " + userId + " successfully unblocked!");
        }
    }
}
