package com.apnacart.users.controllers;

import com.apnacart.users.UserDto;
import com.apnacart.users.exception.UserNotFoundException;
import com.apnacart.users.entities.User;
import com.apnacart.users.services.AddressService;
import com.apnacart.users.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    private AddressService addressService;

    public UserController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            if(user!=null){
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (RuntimeException e){

            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found, Error!", e);
//            throw new UserNotFoundException(id);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserList(){
        List<User> userList = userService.getAllUsers();

        if(userList != null){
            return ResponseEntity.status(HttpStatus.OK).body(userList);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
       try {
           Long userId = userService.createUser(userDto);
           if(userId!=null){
               return ResponseEntity.status(HttpStatus.OK).body(userId);
           }else {
               return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Error");
           }
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
//        logger.error("Requested URL="+request.getRequestURL());
//        logger.error("Exception Raised="+ex);
//
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setMessage(ex.getMessage());
//        errorResponse.setUrl(request.getRequestURL().toString());
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//    }
}
