package com.app.journalApp.service;

import com.app.journalApp.Entity.JournalEntity;
import com.app.journalApp.Entity.UserEntity;
import com.app.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserEntryRepository uer;

    //    saving the Journal Entry
    public UserEntity saveUser(UserEntity user) {
        return uer.save(user);
    }

//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public UserEntity saveNewUser(UserEntity user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setRoles(Arrays.asList("user"));
        return uer.save(user);
    }

    //    getting all Entries
    public List<UserEntity> getAllUsers() {
        return uer.findAll();
    }

    //    find by id
    public Optional<UserEntity> findUserById(ObjectId id) {
        return uer.findById(id);
    }

    //    delete by id
    public void deleteUserById(ObjectId id) {
        uer.deleteById(id);
    }

    public UserEntity findUserByName(String name) {
        return uer.findByUserName(name);
    }

}
