package com.app.journalApp.controller;

import com.app.journalApp.Entity.JournalEntity;
import com.app.journalApp.Entity.UserEntity;
import com.app.journalApp.service.JournalEntryService;
import com.app.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
//    (IMP) Controller Should always call the Service Repo methods
//    Controller only create the endpoints and the inner implementation logic be settled by Service layer


    @Autowired
//    Service injected for use
    private JournalEntryService jrService;

    //    userService
    @Autowired
    private UserService usrService;

//    here we are saving journal entry with a foreign field username bcz of many-one mapping
//    every journal have a field user which specify which user the Journal entry belongs
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntity> createJournalEntry(@RequestBody JournalEntity myEntry,@PathVariable String userName) {
        try {
            myEntry.setDate(LocalDateTime.now());
            jrService.saveEntry(myEntry,userName);

            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }


//    getting all the Journal entries of a user
    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntity>> getAllEntriesOfUser(@PathVariable String userName) {
        UserEntity user = usrService.findUserByName(userName);
        List<JournalEntity> all = user.getEntryList();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    getting journal entry by id
    @GetMapping("byId/{id}")
    public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntity> myEntity = jrService.findEntryById(id);
        if (myEntity.isPresent()) {
            return new ResponseEntity<>(myEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    updating the journal entry and then adding it to user
    @PutMapping("byId/{userName}/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntity newUpEntry,@PathVariable String userName) {
        JournalEntity oldEntry = jrService.findEntryById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(newUpEntry.getTitle() != null && !newUpEntry.getTitle().equals("") ? newUpEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newUpEntry.getContent() != null && !newUpEntry.getContent().equals("") ? newUpEntry.getContent() : oldEntry.getContent());
//            this been added to 1 args saveEntry method
            jrService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    deleting the journal Entry and also removing that entry from entryList of user also
    @DeleteMapping("byId/{userName}/{id}")
//    ResponseEntity<?> here ? is a wildcard character by this it can return any object irrespective of type
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id,@PathVariable String userName) {
        jrService.deleteEntryById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
