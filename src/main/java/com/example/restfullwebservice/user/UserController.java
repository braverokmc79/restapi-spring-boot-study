package com.example.restfullwebservice.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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



    //https://www.inflearn.com/questions/203512/entitymodel-deprecated-어떻게-바꾸면-될까요
    //GET /users/1 or /users/10 -> String
    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> retrieveUser(@PathVariable int id){
        User user =service.findOne(id);
        if(user==null){
            log.info(" 유저 없음");
            throw new UserNotFoundException(String.format("ID[%s] not found ", id) );
        }

        System.out.println("********  user  : " +user.toString());
        //HATEOAS
        EntityModel<User> entityModel  = EntityModel.of(user);



        WebMvcLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel .add(linkTo.withRel("all-users"));

        return ResponseEntity.ok(entityModel );
    }




    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid  @RequestBody User user){
        User savedUser =service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
//        log.info(" *****  uri {} ", uri);
        //ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }



    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user =service.deleteById(id);
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found ", id));
        }

    }



}
