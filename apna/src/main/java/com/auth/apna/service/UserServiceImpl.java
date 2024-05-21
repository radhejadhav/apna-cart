package com.auth.apna.service;

import com.auth.apna.dao.RoleRepository;
import com.auth.apna.dao.UserRepository;
import com.auth.apna.dto.UserDto;
import com.auth.apna.entity.User;
import com.auth.apna.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("user not found!"));
    }

    public UserRole getRoleByName(String role){
        UserRole userRole = null;
        logger.info("fetching roles...-"+role);
        try {
            userRole = roleRepository.findUserRoleByRole(role);
            logger.info("roles:: "+ userRole);
        }catch (Exception e){
            logger.warn("Role name not found::-"+role);
            logger.error(e.getMessage());
        }
        if(userRole==null){
            logger.info("Role not found:: "+role);
            userRole = new UserRole();
            userRole.setRole(role);
            roleRepository.saveAndFlush(userRole);
            logger.info("Created new role:: "+ role);
        }
        return userRole;
    }

    @Override
    public User createUser(UserDto userDto) {

        User user = User.builder()
                .setName(userDto.getName())
                .setUsername(userDto.getUsername())
                .setPassword(passwordEncoder.encode(userDto.getPassword()))
                .setContact(userDto.getContact())
                .setRoles(new HashSet<>(new HashSet<>(userDto.getRoles().stream().map(this::getRoleByName).toList())))
                .setAccountNonExpired(true)
                .setCredentialsNonExpired(true)
                .setAccountNonLocked(true)
                .setEnabled(true)
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(UserDto userDto, Long id) {
        return null;
    }

    @Override
    public Long deleteUserById(Long id) {
         try {
             userRepository.deleteById(id);
             logger.warn("user deleted::"+id);
             return id;
         }catch (Exception e){
             logger.error(e.getMessage());
             throw new RuntimeException("Unable to delete user::" +id);
         }
    }
}
