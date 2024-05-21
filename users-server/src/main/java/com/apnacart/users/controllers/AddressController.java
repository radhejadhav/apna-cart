package com.apnacart.users.controllers;

import com.apnacart.users.UserDto;
import com.apnacart.users.entities.Address;
import com.apnacart.users.services.AddressService;
import jakarta.ws.rs.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

    private AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<?> addAddress(@RequestBody UserDto.Address address, @RequestParam String username){
        return ResponseEntity.status(HttpStatus.OK).body(service.createAddress(address, username));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserAddress(@PathVariable Long userId){
        List<Address> addressList = service.getAddressByUser(userId);
        if(addressList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No address found for the user: "+ userId);
        }else return ResponseEntity.status(HttpStatus.OK).body(addressList);
    }
}
