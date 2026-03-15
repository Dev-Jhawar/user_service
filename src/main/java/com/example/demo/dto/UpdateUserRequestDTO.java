package com.example.demo.dto;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Role role;
    private String number;
    private String address;

}
