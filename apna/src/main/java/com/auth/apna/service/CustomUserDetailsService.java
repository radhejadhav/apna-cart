package com.auth.apna.service;

import com.auth.apna.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
           UserDetails userDetails = userRepository.findUserByUsername(username);
           logger.info("Found User:: "+ userDetails.getUsername());
           return userDetails;
        }catch (Exception e){
            logger.error("Username not found: "+username+"\n"+e.getMessage());
        }
        return null;
    }
}
