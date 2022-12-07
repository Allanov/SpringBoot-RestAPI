package com.peaksoft.springbootrestapi.repository;

import com.peaksoft.springbootrestapi.dto.response.UserResponse;
import com.peaksoft.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.peaksoft.springbootrestapi.dto.UserResponse(u.id, u.name, u.age, u.email) from User u where u.id = :userId ")
    UserResponse getUserById(Long userId);

    @Query("select new com.peaksoft.springbootrestapi.dto.UserResponse(u.id, u.name, u.age, u.email) from User u where u.isBlocked = :isBlocked") // true
    List<UserResponse> findAll(Boolean isBlocked);
}