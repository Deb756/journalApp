package com.app.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journals")
@Data
public class JournalEntity {

    @Id
    private ObjectId id;

    private String title;
    private String content;
    private LocalDateTime date = LocalDateTime.now();

    // Reference back to the user (many-to-one)
//    dbref use to make relation/mapping with other table through a foreign key here its user
    @DBRef
    @JsonBackReference
    private UserEntity user;
}
