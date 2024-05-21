package com.auth.apna.dao;

import com.auth.apna.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUsername(String username);
}
