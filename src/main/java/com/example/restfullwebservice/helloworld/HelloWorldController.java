package com.example.restfullwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {


    @Autowired
    private MessageSource messageSource;


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



    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language" , required = false)   Locale locale){

        return  messageSource.getMessage("greeting.message", null, locale);

    }




}
