package com.apati147.springsecurityjwt.userauth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("apati147")) {
            return new User("apati147", "@kshay123", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found!!");
        }
    }
}
