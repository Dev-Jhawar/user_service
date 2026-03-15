package com.example.demo.service;

import com.example.demo.dto.CreateUserRequestDTO;
import com.example.demo.dto.UpdateUserRequestDTO;
import com.example.demo.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(CreateUserRequestDTO user);
    UserResponseDTO updateUser(Long id, UpdateUserRequestDTO user);
    void deleteUser(Long id);
}
