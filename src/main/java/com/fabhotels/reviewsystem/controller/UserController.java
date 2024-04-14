package com.fabhotels.reviewsystem.controller;

import com.fabhotels.reviewsystem.dto.ResponseObj;
import com.fabhotels.reviewsystem.dto.UserDto;
import com.fabhotels.reviewsystem.service.UserService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/reviewsystem")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    @GetMapping(value = "/users")
    public ResponseObj fetchAllUsers(){
        return userService.fetchAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseObj getUserById(@PathVariable(value = "id") String id) throws Exception {
        return userService.fetchUserById(Integer.valueOf(id));
    }
}
