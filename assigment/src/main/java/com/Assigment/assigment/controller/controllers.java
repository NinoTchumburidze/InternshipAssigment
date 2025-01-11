package com.Assigment.assigment.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllers {

    @GetMapping(value = "/")
    public String getPage(){
        return "welcome";
    }

}
