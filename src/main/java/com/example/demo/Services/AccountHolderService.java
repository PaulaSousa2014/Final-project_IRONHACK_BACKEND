package com.example.demo.Services;

import com.example.demo.Models.Account.Account;
import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.User;
import com.example.demo.Repositories.Account.AccountRepository;
import com.example.demo.Repositories.User.AccountHolderRepository;
import com.example.demo.Repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class AccountHolderService {

@Autowired
    UserRepository userRepository;

@Autowired
    AccountHolderRepository accountHolderRepository;

@Autowired
    AccountRepository accountRepository;


    // Method to access balance for own account

    public BigDecimal accessBalance(Long accountId, Long userId) {
        Account result = null;
        if (userRepository.findById(userId).isPresent()) {
            AccountHolder accountHolder = accountHolderRepository.findById(userId).get();
            for (Account ac : accountHolder.getPrimaryAccountList()){
                if (ac.getAccountId() == accountId) {
                    result = accountRepository.findById(accountId).get();
                } else{
                    throw new IllegalArgumentException("Do not have access to this account");
                }
            }
            for (Account ac : accountHolder.getSecondaryAccountList()){
                if (ac.getAccountId() == accountId) {
                    result = accountRepository.findById(accountId).get();
                }else{
                    throw new IllegalArgumentException("Do not have access to this account");
                }
            }
        }else {
            throw new IllegalArgumentException("The account does not exist");
        }

        return result.getBalance();
    }


    // Method to transfer money from one accounts to other account

    public void transferMoney(String senderName, Long accountId, BigDecimal amount, Long receiverAccountId){
        if(userRepository.findByName(senderName).isPresent()) {
            User user = userRepository.findByName(senderName).get();
            AccountHolder accountHolder = (AccountHolder) user;
            Account account = null;
            for (Account ac : accountHolder.getPrimaryAccountList()) {
                if(ac.getAccountId() == accountId) {
                    account = accountRepository.findById(accountId).get();
                }
            }
            if(account ==null) throw new IllegalArgumentException("The account does not exist");
            if(account.getBalance().compareTo(amount) < 0) throw new IllegalArgumentException("Not sufficient balance");

            if(accountRepository.findById(receiverAccountId).isPresent()) {
                Account recAccount = accountRepository.findById(receiverAccountId).get();
                recAccount.setBalance(recAccount.getBalance().add(amount));
                account.setBalance(account.getBalance().subtract(amount));
                accountRepository.save(recAccount);
                accountRepository.save(account);
            }

            throw new IllegalArgumentException("The recipient account does not exist");
        }

        throw  new IllegalArgumentException("Sender does not exist");
    }


}
