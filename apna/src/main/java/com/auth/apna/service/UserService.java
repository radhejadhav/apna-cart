package com.auth.apna.service;

import com.auth.apna.dto.UserDto;
import com.auth.apna.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> fetchAllUsers();
    User findUserById(Long id);
    User createUser(UserDto userDto);
    User updateUser(UserDto userDto, Long id);
    Long deleteUserById(Long id);
}
