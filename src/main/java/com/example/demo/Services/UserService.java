package com.example.demo.Services;

import com.example.demo.Models.User.*;
import com.example.demo.Models.User.Dto.UserDto;
import com.example.demo.Repositories.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    ThirdPartUserRepository thirdPartUserRepository;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RoleRepository roleRepository;


    public User addUser(UserDto user) {
        User newUser = new User( user.getUserNameDto(), passwordEncoder.encode(user.getUserPasswordDto()));
        userRepository.save(newUser);
        Role role = roleRepository.save(new Role("USER", newUser));
        return newUser;
    }

    //method to add new AccountHolder user

    public AccountHolder addAccountHolder(UserDto newAccountHolder){
        System.out.println("PASSWORD: " + newAccountHolder.getUserPasswordDto());
        AccountHolder accountHolder = new AccountHolder(
                newAccountHolder.getUserNameDto(),
                passwordEncoder.encode(newAccountHolder.getUserPasswordDto()),
                newAccountHolder.getUserPrimaryAccountListDto(),
                newAccountHolder.getUserSecondaryAccountListDto(),
                newAccountHolder.getUserDateOfBirthDto(),
                newAccountHolder.getUserPrimaryAddressDto(),
                newAccountHolder.getUserSecondaryAddressDto()
        );
        AccountHolder newUser = accountHolderRepository.save(accountHolder);
        Role role = roleRepository.save(new Role("ACCOUNT_HOLDER", accountHolder));
        return newUser;
    }

    //method to add new third-party user

    public ThirdPartUser addThirdPartUser(UserDto newThirdPartUser){
        ThirdPartUser thirdPartUser = new ThirdPartUser(
                newThirdPartUser.getUserNameDto(),
                passwordEncoder.encode(newThirdPartUser.getUserPasswordDto()),
                newThirdPartUser.getUserHashedKey()
        );
        ThirdPartUser newUser = thirdPartUserRepository.save(thirdPartUser);
        Role role = roleRepository.save(new Role("THIRD_PART_USER", thirdPartUser));
        return newUser;
    }

    //method to add new Admin

    public Admin addAdmin(UserDto newAdmin){
        Admin admin = new Admin(
            newAdmin.getUserNameDto(),
                passwordEncoder.encode(newAdmin.getUserPasswordDto())
        );
        Admin newUser = adminRepository.save(admin);
        Role role = roleRepository.save(new Role("ADMIN", admin));
        return newUser;
    }


    public void modifyPassword( String password, UserDetails userDetails) {
        User user = userRepository.findByName(userDetails.getUsername()).get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }




}