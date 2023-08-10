package com.example.restfullwebservice.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserDaoService service;

//    public UserController(UserDaoService service){
//        this.service=service;
//    }
//

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
       return service.findAll();
    }

    //GET /users/1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user =service.findOne(id);
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%] not found ", id) );
        }
        return service.findOne(id);
    }




    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser =service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
//        log.info(" *****  uri {} ", uri);

        //ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }










}
