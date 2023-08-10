package com.example.restfullwebservice.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    //GET
    // /hello-world (endpoint)
   // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "Hello World";
    }


    //alt + enter
    @GetMapping(path="/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World  , %s"  , name) );
    }





}
