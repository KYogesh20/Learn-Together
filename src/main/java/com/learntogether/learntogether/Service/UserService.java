package com.learntogether.learntogether.Service;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learntogether.learntogether.Dto.RegisterRequestDto;
import com.learntogether.learntogether.Dto.RegisterResponseDto;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Enum.UserStatus;
import com.learntogether.learntogether.Exception.UserAlreadyExistsException;
import com.learntogether.learntogether.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
public void createUser(RegisterRequestDto registerRequestDto) throws UserAlreadyExistsException{

        User user = userRepository.findByEmail(registerRequestDto.getEmail());
        if(user!=null) throw new UserAlreadyExistsException("User already exists");

        User newUser = new User();
        newUser.setFirstname(registerRequestDto.getFirstname());
        newUser.setLastname(registerRequestDto.getLastname());
        newUser.setEmail(registerRequestDto.getEmail());
        newUser.setUsername(registerRequestDto.getUsername());
        newUser.setPassword(registerRequestDto.getPassword());
        newUser.setUserStatus(UserStatus.ACTIVE);

        userRepository.save(newUser);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
