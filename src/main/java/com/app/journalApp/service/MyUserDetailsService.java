package com.app.journalApp.service;

import com.app.journalApp.Entity.UserEntity;
import com.app.journalApp.Entity.UserPrinciple;
import com.app.journalApp.repository.UserEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserEntryRepository userEntryRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userEntryRepository.findByUserName(username);
        if(user == null) throw new UsernameNotFoundException("User Not found");

        return new UserPrinciple(user);
    }
}