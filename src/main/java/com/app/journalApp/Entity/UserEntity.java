package com.app.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class UserEntity {

    @Id
    private ObjectId id;

    @NonNull
//    it's a lombok an annotation which never let the field empty if empty - then exception
    @Indexed(unique = true)
//    indexed is mongoJpa annotation used to specify the unique value for every username - duplicate not allowed
    private String userName;

    @NonNull
    private String password;

    private List<String> roles;

    //  One user has many journal entries
    @DBRef(lazy = true)   // lazy = don’t fetch journals unless needed
    @JsonManagedReference
//    this ignores the infinite recursion due to JournalEntity has a reference back to the UserEntity.Which again has an entryList… and so on… ♾️
    private List<JournalEntity> entryList = new ArrayList<>();
}

