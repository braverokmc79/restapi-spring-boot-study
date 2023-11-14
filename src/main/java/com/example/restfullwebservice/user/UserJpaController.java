package com.example.restfullwebservice.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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


    /**
     * [Spring] RestAPI와 Hateoas로 링크 삽입하기*
     *
     * https://katastrophe.tistory.com/160
     * @param id
     * @return
     *
     *
     * http://localhost:8088/jpa/users/1
     * 
     * 출력 예
     *
     * {
     *     "name": "User1",
     *     "joinDate": "2023-11-13T15:00:00.000+00:00",
     *     "ssn": "701010-11111",
     *     "_links": {
     *         "all-users": {
     *             "href": "http://localhost:8088/jpa/users"
     *         }
     *     }
     * }
     */

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable  int id){
        Optional<User> user =userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException(String.format("ID{%s} not found", id));
        }

       // Resource
        EntityModel<User> userEntityModel=EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo= linkTo(methodOn(this.getClass()).retrieveAllUsers());
        userEntityModel.add(linkTo.withRel("all-users"));

        return userEntityModel;
    }






}
