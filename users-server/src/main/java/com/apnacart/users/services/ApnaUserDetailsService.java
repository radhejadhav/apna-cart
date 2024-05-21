package com.apnacart.users.services;

import com.apnacart.users.dao.UserRepository;
import com.apnacart.users.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApnaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public ApnaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  userRepository.findUserByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("not found "+ username));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .authorities(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ROLE_USER";
                    }
                })
                .password(user.getPassword())
                .build();
    }
}
