package com.app.journalApp.service;

import com.app.journalApp.Entity.JournalEntity;
import com.app.journalApp.Entity.UserEntity;
import com.app.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
//    to enable spring to create its obj (dependency injection)
    private JournalEntryRepository jrRepo;

//    user Service
    @Autowired
    private UserService usrService;


    //    saving the user with Journal Entry
//    @Transactional Used here to achieve Atomicity and Isolation in database
//    simply make this method to a bundle / unit if any issue come it will roll_back in db
    @Transactional
    public JournalEntity saveEntry(JournalEntity myEntry, String userName) {
        UserEntity user = usrService.findUserByName(userName);
        myEntry.setUser(user);
        JournalEntity saved = jrRepo.save(myEntry);
        user.getEntryList().add(saved);
//        user.setUserName(null);
        usrService.saveUser(user);
        return saved;
    }

    //    updating the user with Journal Entry
    public JournalEntity saveEntry(JournalEntity myEntry) {
        return jrRepo.save(myEntry);
    }


    //    getting all Entries
    public List<JournalEntity> getAllEntries() {
        return jrRepo.findAll();
    }

    //    find by id
    public Optional<JournalEntity> findEntryById(ObjectId id) {
        return jrRepo.findById(id);
    }

    //    delete by id
    public void deleteEntryById(ObjectId id,String userName) {
        UserEntity user = usrService.findUserByName(userName);
        user.getEntryList().removeIf(entry -> entry.getId().equals(id));
        usrService.saveUser(user);
        jrRepo.deleteById(id);
    }


}
