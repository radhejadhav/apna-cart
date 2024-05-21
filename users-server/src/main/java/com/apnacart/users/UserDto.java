package com.apnacart.users;

import com.apnacart.users.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

public class UserDto {

    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "username is required!!")
    private String username;
    @NotBlank(message = "password is required!!")
    private String password;
    private List<Address> addresses;

    public static class Address {
        private String house;
        private String street;
        private Long areaPin;
        private String city;
        private String state;

        private User user;

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public Long getAreaPin() {
            return areaPin;
        }

        public void setAreaPin(Long areaPin) {
            this.areaPin = areaPin;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "house='" + house + '\'' +
                    ", street='" + street + '\'' +
                    ", areaPin=" + areaPin +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddresses(List<Address> addresses) {

        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
