package com.example.demo.Controller.Interfaces;

import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.Admin;
import com.example.demo.Models.User.Dto.UserDto;
import com.example.demo.Models.User.ThirdPartUser;
import com.example.demo.Models.User.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserInterface {
    public User addUser(UserDto user);
    public AccountHolder addAccountHolder(UserDto newAccountHolder);
    public ThirdPartUser addThirdPartUser(UserDto newThirdPartUser);
    public Admin addAdmin(UserDto newAdmin);
    public void modifyPassword( String password, UserDetails userDetails);
}
