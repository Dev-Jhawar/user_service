package com.example.demo.serviceImpl;

import com.example.demo.dto.CreateUserRequestDTO;
import com.example.demo.dto.UpdateUserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repo.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found."));

        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO createUser) {
        User user = UserMapper.toEntity(createUser);
        return UserMapper.toDTO(repo.save(user));
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdateUserRequestDTO updateDetails) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID is Provided."));

        user.setFirstName(updateDetails.getFirstName());
        user.setLastName(updateDetails.getLastName());
        user.setEmail(updateDetails.getEmail());
        user.setGender(updateDetails.getGender());
        user.setRole(updateDetails.getRole());
        user.setNumber(updateDetails.getNumber());
        user.setAddress(updateDetails.getAddress());

        User updatedUser = repo.save(user);

        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid ID is Provided."));

        if(user != null) {
            repo.deleteById(id);
        }
    }
}
