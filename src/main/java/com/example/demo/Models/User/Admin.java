package com.example.demo.Models.User;

import com.example.demo.Models.User.User;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin(String name, String password) {
        super(name, password);
    }

    public Admin() {
    }




}
