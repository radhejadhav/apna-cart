package com.apnacart.users.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
    @SequenceGenerator(name = "seq_id", sequenceName = "ID_GENERATOR")
    private Long id;
    private String house;
    private String street;
    private Long areaPin;
    private String city;
    private String state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                "id=" + id +
                ", house='" + house + '\'' +
                ", street='" + street + '\'' +
                ", areaPin=" + areaPin +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", user=" + user +
                '}';
    }
}
