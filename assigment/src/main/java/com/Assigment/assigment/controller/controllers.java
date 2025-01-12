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

//valid:
//curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":5,\"cost\":10},{\"weight\":10,\"cost\":20},{\"weight\":3,\"cost\":5},{\"weight\":8,\"cost\":15}]}'
//valid vol4
//curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":20,\"availableTransfers\":[{\"weight\":10,\"cost\":30},{\"weight\":15,\"cost\":25},{\"weight\":5,\"cost\":15},{\"weight\":12,\"cost\":18}]}'
//valid vol3
//curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":4,\"cost\":8},{\"weight\":6,\"cost\":12},{\"weight\":7,\"cost\":14},{\"weight\":5,\"cost\":10}]}'
//exceeding limit:
//curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":20,\"cost\":10},{\"weight\":25,\"cost\":20},{\"weight\":18,\"cost\":5},{\"weight\":30,\"cost\":15}]}'