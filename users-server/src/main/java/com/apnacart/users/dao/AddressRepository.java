package com.apnacart.users.dao;

import com.apnacart.users.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select * from Address a where a.user_id=?1", nativeQuery = true )
    List<Address> findAddressByUserId(Long userId);
}
