package com.example.demo.grpc;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void getUserById(UserRequest request,
                            StreamObserver<UserResponse> responseObserver) {

        UserResponseDTO dto = userService.getUserById(request.getId());

        UserResponse response = UserResponse.newBuilder()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setGender(dto.getGender().name())
                .setRole(dto.getRole().name())
                .setNumber(dto.getNumber())
                .setAddress(dto.getAddress())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllUsers(Empty request,
                            StreamObserver<UserList> responseObserver) {

        List<UserResponseDTO> users = userService.getAllUsers();

        UserList.Builder listBuilder = UserList.newBuilder();

        for (UserResponseDTO dto : users) {
            UserResponse user = UserResponse.newBuilder()
                    .setId(dto.getId())
                    .setFirstName(dto.getFirstName())
                    .setLastName(dto.getLastName())
                    .setEmail(dto.getEmail())
                    .setGender(dto.getGender().name())
                    .setRole(dto.getRole().name())
                    .setNumber(dto.getNumber())
                    .setAddress(dto.getAddress())
                    .build();

            listBuilder.addUsers(user);
        }

        responseObserver.onNext(listBuilder.build());
        responseObserver.onCompleted();
    }
}