package com.apnacart.users.services;

import com.apnacart.users.UserDto;
import com.apnacart.users.dao.UserRepository;
import com.apnacart.users.entities.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    UserRepository repository;

    ModelMapper mapper;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = repository.findAll();
        if(userList.size()>0){
            logger.info("users found! size: "+userList.size());
            return userList;
        }else {
            logger.warn("No user found!");
            return null;
        }
    }

    @Override
    @Transactional
    public Long createUser(UserDto userDto) {
        try {
            User user = mapper.map(userDto, User.class);
            user.getAddresses().forEach(address -> address.setUser(user));
            repository.save(user);
            logger.info("user saved successfully! with id: "+user.getId());
            return user.getId();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getUserById(Long userId) {
       try {
           User user = repository.findById(userId).orElse(null);
           if(user!=null) {
                logger.info("User found!");
                return user;
           }else {
               logger.warn("User not found");
               throw new Exception("Not Found");
           }
       }catch (Exception e){
           throw new RuntimeException("Exception while getting user.");
       }
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = repository.findUserByUsername(username);
        return optionalUser.orElseGet(()->{
            logger.warn("user not found for username: "+username);
            return null;
        });
    }

    public void createUsers(){
        UserDto adminUser  = new UserDto();
        UserDto.Address address = new UserDto.Address();
//        address
        adminUser.setName("Admin User");
        adminUser.setUsername("admin@user.com");
        adminUser.setAddresses(List.of());
    }
}
