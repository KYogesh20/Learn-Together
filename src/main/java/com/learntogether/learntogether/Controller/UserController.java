package com.learntogether.learntogether.Controller;

import com.learntogether.learntogether.Dto.RegisterRequestDto;
import com.learntogether.learntogether.Dto.RegisterResponseDto;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody RegisterRequestDto registerRequestDto){
        try{
            userService.createUser(registerRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User created Successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/get-users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
