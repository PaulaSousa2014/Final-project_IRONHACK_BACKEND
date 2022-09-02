package com.example.demo.Models.User;

import com.example.demo.Models.User.User;

import javax.persistence.Entity;

@Entity
public class ThirdPartUser extends User {



    private String hashedKey;


    public ThirdPartUser(String name, String password, String hashedKey) {
        super(name, password);
        this.hashedKey = hashedKey;
    }

    public ThirdPartUser() {
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
