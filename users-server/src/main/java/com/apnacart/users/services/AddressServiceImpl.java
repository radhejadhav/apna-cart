package com.apnacart.users.services;

import com.apnacart.users.UserDto;
import com.apnacart.users.dao.AddressRepository;
import com.apnacart.users.entities.Address;
import com.apnacart.users.entities.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private AddressRepository repository;

    private UserService userService;

    private ModelMapper mapper;

    public AddressServiceImpl(AddressRepository repository, UserService userService, ModelMapper mapper) {
        this.repository = repository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public Long createAddress(UserDto.Address address, String username) {
        Address newAddress = null;
        try {
            User user = userService.getUserByUsername(username);
            newAddress = mapper.map(address, Address.class);
            newAddress.setUser(user);
            repository.save(newAddress);
            if(newAddress.getId()!=null)
                logger.info("created new address for user: "+username+" address id: "+newAddress.getId());
            else logger.warn("Address not been created!");
        }catch (Exception e){
            logger.error("Exception while adding new address"+e.getMessage());
        }
        return newAddress.getId();
    }

    @Override
    public List<Address> getAddressByUser(Long userId) {
        List<Address> addresses = null;
        try {
            addresses = repository.findAddressByUserId(userId);

            if(addresses!= null&& addresses.size()>0)
                logger.info("Found address: "+addresses.size());
            else logger.warn("No addresses found!");
        }catch (Exception e){
            logger.error("Exception while getting address: "+e.getMessage());
        }
        return addresses;
    }
}
