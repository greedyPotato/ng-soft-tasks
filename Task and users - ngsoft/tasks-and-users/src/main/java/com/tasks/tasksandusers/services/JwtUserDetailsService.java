package com.tasks.tasksandusers.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.tasks.tasksandusers.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{

    public static final String USER = "USER";//MOVE TO UTILS

    public static final String ADMIN = "ADMIN";//MOVE TO UTILS

    public static final String ROLE_USER = "ROLE_" + USER;//MOVE TO UTILS

    public static final String ROLE_ADMIN = "ROLE_" + ADMIN;//MOVE TO UTILS

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        final com.tasks.tasksandusers.entity.User user = userRepository.findByUserName(username).orElseThrow(
            () -> new UsernameNotFoundException("User " + username + " not found"));
            if(user.getRole().name().equals(USER))
                return  new User(username, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
            else
                return  new User(username, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(ROLE_ADMIN)));
    }
    
}
