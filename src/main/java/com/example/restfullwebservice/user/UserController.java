package com.example.restfullwebservice.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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
    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
       return service.findAll();
    }



    //https://www.inflearn.com/questions/203512/entitymodel-deprecated-어떻게-바꾸면-될까요
    //GET /users/1 or /users/10 -> String

    /**
     * 다음을 참조
     * https://github.dev/braverokmc79/rest-api-width-spring
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<?> retrieveUser(@PathVariable int id){
        User user =service.findOne(id);
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s] not found ", id) );
        }

        /**
         * 링크 생성하기
         * EntityModel.of(newEvent); Resource 객체를 가져와서 사용
         */
//        WebMvcLinkBuilder selfLinkBuilder=linkTo(UserController.class).slash(id);
//        URI createdUri=selfLinkBuilder.toUri();
//        log.info("*  createdUri  {} " , createdUri);

        //1.HATEOAS
        EntityModel<User> entityModel  = EntityModel.of(user);

        //2. 링크 추가 모든 유저보기 링크추가
        WebMvcLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        
        //간소화 한줄 처리 - 셀프 링크 추가
        entityModel.add( linkTo(methodOn(this.getClass()).retrieveUser(id)).withSelfRel() );

        //3.반환처리
        //return ResponseEntity.status(HttpStatus.OK).body(entityModel);
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







}
