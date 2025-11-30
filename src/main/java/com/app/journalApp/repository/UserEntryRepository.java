package com.app.journalApp.repository;

import com.app.journalApp.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepository extends MongoRepository<UserEntity, ObjectId> {
//    custom method
    UserEntity findByUserName(String userName);
}
