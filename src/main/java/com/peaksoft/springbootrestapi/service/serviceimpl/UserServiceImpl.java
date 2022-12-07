package com.peaksoft.springbootrestapi.service.serviceimpl;

import com.peaksoft.springbootrestapi.dto.request.UserRequest;
import com.peaksoft.springbootrestapi.dto.response.UserResponse;
import com.peaksoft.springbootrestapi.entity.User;
import com.peaksoft.springbootrestapi.repository.UserRepository;
import com.peaksoft.springbootrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public User saveUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user.setCreationDate(LocalDateTime.now());
        user.setIsBlocked(false);

        return userRepo.save(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepo.findById(userId).get();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        userRepo.save(user);

        return new UserResponse(user.getId(), user.getName(), user.getAge(), user.getEmail());
    }

    @Override
    public UserResponse getUserById(Long userId) {
//        User user = userRepo.findById(userId).get();
//        return new UserResponse(user.getId(), user.getName(), user.getAge(), user.getEmail());
        return userRepo.getUserById(userId);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public List<UserResponse> getAllUsers(Boolean isBlocked) {
        return userRepo.findAll(isBlocked);
    }

    @Override
    public void blockUserOrUnblock(Long userId, Boolean block) {
        User user = userRepo.findById(userId).get();
        user.setIsBlocked(block);
        userRepo.save(user);
    }
}
