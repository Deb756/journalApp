package com.app.journalApp.controller;

import com.app.journalApp.Entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    Map<String, JournalEntity> entry = new HashMap<>();

    //    creating the entries by @postMapping
    @PostMapping
    public boolean createJornalEntry(@RequestBody JournalEntity myEntry) {
//        entry.put(myEntry.getId(), myEntry);
        return true;
    }

    //    getting the entries
    @GetMapping
    public List<JournalEntity> getAllEntries() {
        return new ArrayList<>(entry.values());
    }

    //    argumented mapping and pathvariable access
    //    getting data from URL
    @GetMapping("byId/{id}")
    public JournalEntity getEntryById(@PathVariable Long id) {
        return entry.get(id);
    }

    //    updating the Entry
    @PutMapping("byId/{id}")
    public JournalEntity updateEntry(@PathVariable String id, @RequestBody JournalEntity myEntry) {

        return entry.put(id, myEntry);
    }

    //   deleting the Entry
    @DeleteMapping("byId/{id}")
    public JournalEntity deleteEntry(@PathVariable Long id) {
        return entry.remove(id);
    }
}
