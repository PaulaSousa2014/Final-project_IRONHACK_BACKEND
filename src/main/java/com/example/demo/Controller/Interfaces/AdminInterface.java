package com.example.demo.Controller.Interfaces;

import com.example.demo.Models.Account.*;
import com.example.demo.Models.Account.Dto.AccountDto;
import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.Admin;
import com.example.demo.Models.User.ThirdPartUser;
import com.example.demo.Models.User.User;

import java.math.BigDecimal;


public interface AdminInterface {

    public Account addCheckingAccount(AccountDto newCheckingAccount);

    public Saving addSavingAccount(AccountDto newSavingAccount);
    public CreditCard addCreditCardAccount(AccountDto newCreditCardAccount);
    public BigDecimal getBalance(Long accountId);
    public BigDecimal setBalance(Long accountId,BigDecimal balance);
    public void deleteAccount(Long accountId);




}
