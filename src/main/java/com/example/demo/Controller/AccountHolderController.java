package com.example.demo.Controller;

import com.example.demo.Controller.Interfaces.AccountHolderInterface;
import com.example.demo.Services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController

public class AccountHolderController implements AccountHolderInterface {

    @Autowired
    AccountHolderService accountHolderService;


    @Override
    @GetMapping("/get-balance/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BigDecimal accessBalance(@RequestParam Long accountId, @PathVariable(name = "id") Long userId) {
        return accountHolderService.accessBalance(accountId,userId);
    }

    @Override
    @PutMapping("/account-transfer/{id}/{recId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void transferMoney(@RequestParam String senderName, @PathVariable(name = "id") Long accountId, @RequestParam BigDecimal amount, @PathVariable(name = "recId") Long receiverAccountId) {

    }
}
