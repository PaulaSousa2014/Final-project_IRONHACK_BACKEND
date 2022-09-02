package com.example.demo.Controller;

import com.example.demo.Controller.Interfaces.UserInterface;
import com.example.demo.Models.User.*;
import com.example.demo.Models.User.Dto.UserDto;
import com.example.demo.Repositories.User.RoleRepository;
import com.example.demo.Repositories.User.UserRepository;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController implements UserInterface {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/new-user")
    @ResponseStatus( value= HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }


    @PostMapping("/new-accountHolder")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@RequestBody UserDto newAccountHolder) {
        return userService.addAccountHolder(newAccountHolder);
    }

    @PostMapping("/new-thirdPartUser")
    @ResponseStatus(value=HttpStatus.CREATED)
    public ThirdPartUser addThirdPartUser(@RequestBody UserDto newThirdPartUser) {
        return userService.addThirdPartUser(newThirdPartUser);
    }

    @PostMapping("/new-admin")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody UserDto newAdmin) {
        return userService.addAdmin(newAdmin);
    }

    @PatchMapping("/modify-password")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void modifyPassword(@RequestParam String password, @AuthenticationPrincipal UserDetails userDetails) {

    }
}
