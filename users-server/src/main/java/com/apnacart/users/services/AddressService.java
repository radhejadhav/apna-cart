package com.apnacart.users.services;

import com.apnacart.users.UserDto;
import com.apnacart.users.entities.Address;

import java.util.List;

public interface AddressService {

    Long createAddress(UserDto.Address address, String username);
    List<Address> getAddressByUser(Long userId);
}
