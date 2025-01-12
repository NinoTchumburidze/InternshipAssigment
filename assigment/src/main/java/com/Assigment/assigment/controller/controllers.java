package com.Assigment.assigment.controller;


import com.Assigment.assigment.model.Transfer;
import com.Assigment.assigment.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controllers {

    private service userService;

    @Autowired
    public controllers(service userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String getPage(){
        return "welcome";
    }

    @PostMapping("/processData")
    public String processData(@RequestBody String inputData) {
        userService.createList(inputData);
        return userService.resultToString();
    }

}

//curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":5,\"cost\":10},{\"weight\":10,\"cost\":20},{\"weight\":3,\"cost\":5},{\"weight\":8,\"cost\":15}]}'
//