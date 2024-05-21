package com.apnacart.users.services;

import com.apnacart.users.UserDto;
import com.apnacart.users.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long userId);
    Long createUser(UserDto userDto);
    User getUserByUsername(String username);
}
