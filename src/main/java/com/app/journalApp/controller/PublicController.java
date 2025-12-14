package com.app.journalApp.controller;

import com.app.journalApp.Entity.UserEntity;
import com.app.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

//    un-authenticated endpoints are here

    @Autowired
    private UserService usr;

    //    adding user
    @PostMapping("/create-user")
    public UserEntity addUser(@RequestBody UserEntity user) {
        return usr.saveUser(user);
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }
}
