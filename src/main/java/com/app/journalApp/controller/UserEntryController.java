package com.app.journalApp.controller;

import com.app.journalApp.Entity.UserEntity;

import com.app.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEntryController {

    @Autowired
    private UserService usr;

    //    adding user
    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity user) {
        return usr.saveUser(user);
    }

    //        get all user
    @GetMapping
    public List<UserEntity> getAllUser() {
        return usr.getAllUsers();
    }

    //        updating user
    @PutMapping("/byName")
    public ResponseEntity<UserEntity> updateUserById(@RequestParam("name") String usrName, @RequestBody UserEntity newUsr) {
        UserEntity oldUsr = usr.findUserByName(usrName);
        if (oldUsr != null) {
            oldUsr.setUserName(newUsr.getUserName());
            oldUsr.setPassword(newUsr.getPassword());
            return new ResponseEntity<>(usr.saveUser(oldUsr), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

