package com.example.restfullwebservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;


    // http://localhost:8088/jpa/users   or http://localhost:8088/users
    @GetMapping("/users")
    public List<User>  retrieveAllUsers(){
        return userRepository.findAll();

    }



}
