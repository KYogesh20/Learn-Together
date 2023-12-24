package com.learntogether.learntogether.Service;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learntogether.learntogether.Dto.RegisterRequestDto;
import com.learntogether.learntogether.Dto.RegisterResponseDto;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Enum.UserStatus;
import com.learntogether.learntogether.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
public RegisterResponseDto createUser(RegisterRequestDto registerRequestDto){
        User newUser = new User();
        newUser.setFirstname(registerRequestDto.getFirstname());
        newUser.setLastname(registerRequestDto.getLastname());
        newUser.setEmail(registerRequestDto.getEmail());
        newUser.setUsername(registerRequestDto.getUsername());
        newUser.setPassword(registerRequestDto.getPassword());
        newUser.setUserStatus(UserStatus.ACTIVE);

        userRepository.save(newUser);

        // set response
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        registerResponseDto.setFirstname(newUser.getFirstname());
        registerResponseDto.setLastname(newUser.getLastname());
        registerResponseDto.setEmail(newUser.getEmail());
        registerResponseDto.setUsername(newUser.getUsername());
        return registerResponseDto;
    }

    @JsonManagedReference
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
