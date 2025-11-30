package com.app.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//used to scan all the method with @Transactional at top of it
@EnableTransactionManagement
public class JournalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalAppApplication.class, args);

    }

    //        to manage transaction in this application making a @Bean Manually for it
    @Bean
    public PlatformTransactionManager transact(MongoDatabaseFactory databaseFactory) {
        return new MongoTransactionManager(databaseFactory);
    }

}
//
//