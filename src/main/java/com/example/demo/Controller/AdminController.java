package com.example.demo.Controller;

import com.example.demo.Controller.Interfaces.AdminInterface;

import com.example.demo.Models.Account.*;
import com.example.demo.Models.Account.Dto.AccountDto;
import com.example.demo.Models.User.*;
import com.example.demo.Repositories.User.AccountHolderRepository;
import com.example.demo.Repositories.User.AdminRepository;
import com.example.demo.Repositories.User.RoleRepository;
import com.example.demo.Repositories.User.UserRepository;
import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController


public class AdminController implements AdminInterface {


    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;






    @Override
    @PostMapping("/add-Account")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Account addCheckingAccount(@RequestBody AccountDto checking) {
        return adminService.addCheckingAccount(checking);
    }

    @Override
    @PostMapping("/add-SavingAccount")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Saving addSavingAccount(@RequestBody AccountDto newSavingAccount) {
        return adminService.addSavingAccount(newSavingAccount);
    }

    @Override
    @PostMapping("/add-CreditCardAccount")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CreditCard addCreditCardAccount(@RequestBody AccountDto newCreditCardAccount) {
        return adminService.addCreditCardAccount(newCreditCardAccount);
    }

    @Override
    @GetMapping("/get-AllBalance/{id}")
    @ResponseStatus(value = HttpStatus.FOUND)
    public BigDecimal getBalance(@PathVariable (name = "id") Long accountId) {
        return adminService.getBalance(accountId);
    }

    @Override
    @PatchMapping("/set-balance/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BigDecimal setBalance(@PathVariable (name = "id") Long accountId, @RequestParam BigDecimal balance) {
        return adminService.setBalance(accountId,balance);
    }

    @Override
    @DeleteMapping("/delete-account/{id}")
    public void deleteAccount(@PathVariable (name = "id") Long accountId) {

    }


}
