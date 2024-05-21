package com.auth.apna.controller;

import com.auth.apna.dto.UserDto;
import com.auth.apna.entity.User;
import com.auth.apna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> fetchAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.fetchAllUsers());
    }

    @GetMapping("/:id")
    public ResponseEntity<?> getUserById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/delete/:id")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(id));
    }
}
