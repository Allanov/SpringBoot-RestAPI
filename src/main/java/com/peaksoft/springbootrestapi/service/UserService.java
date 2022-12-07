package com.peaksoft.springbootrestapi.service;

import com.peaksoft.springbootrestapi.dto.request.UserRequest;
import com.peaksoft.springbootrestapi.dto.response.UserResponse;
import com.peaksoft.springbootrestapi.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(UserRequest user);
    UserResponse updateUser(Long userId, UserRequest userRequest);
    void deleteUserById(Long userId);
    UserResponse getUserById(Long userId);
    List<UserResponse> getAllUsers(Boolean isBlocked);

    void blockUserOrUnblock(Long userId, Boolean block);
}
