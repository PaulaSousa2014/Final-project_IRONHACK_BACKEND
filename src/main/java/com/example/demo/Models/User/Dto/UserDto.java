package com.example.demo.Models.User.Dto;

import com.example.demo.Models.Account.Account;
import com.example.demo.Models.User.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private String userNameDto;
    private String userPasswordDto;
    private List<Account> userPrimaryAccountListDto;
    private List<Account> userSecondaryAccountListDto;
    private LocalDate userDateOfBirthDto;
    private Address userPrimaryAddressDto;
    private Address userSecondaryAddressDto;
    private String userHashedKey;



}
