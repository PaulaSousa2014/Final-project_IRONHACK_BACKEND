package com.example.demo.Services;


import com.example.demo.Models.Account.Account;
import com.example.demo.Models.Account.Checking;
import com.example.demo.Models.Account.GeneralChecking;
import com.example.demo.Models.User.AccountHolder;
import com.example.demo.Models.User.ThirdPartUser;
import com.example.demo.Models.User.User;
import com.example.demo.Repositories.Account.AccountRepository;
import com.example.demo.Repositories.Account.CheckingRepository;
import com.example.demo.Repositories.Account.GeneralCheckingRepository;
import com.example.demo.Repositories.User.ThirdPartUserRepository;
import com.example.demo.Repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartUserService {


    @Autowired
    ThirdPartUserRepository thirdPartUserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    GeneralCheckingRepository generalCheckingRepository;




    //Method to thirdPartUser to send money to other account

    public void sendMoney(String hashedKey, Long accountId, BigDecimal amount, Long accountSecretKey) {
            ThirdPartUser thirdPartUser = thirdPartUserRepository.findByHashedKey(hashedKey);
            if(generalCheckingRepository.findById(accountId).isPresent()) {
                GeneralChecking recAccount = generalCheckingRepository.findById(accountId).get();
              if(generalCheckingRepository.findBySecretKey(accountSecretKey).equals(recAccount.getSecretKey())) {
                   recAccount.setBalance(recAccount.getBalance().add(amount));
                generalCheckingRepository.save(recAccount);
                }
              throw new ResponseStatusException(HttpStatus.CONFLICT,"The SecretKey is not correct");

            }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The recipient account does not exist");

    }
    //Method to thirdPartUser to receive money from other account

    public void receiveMoney(String hashedKey, Long accountId, BigDecimal amount, Long accountSecretKey) {
        ThirdPartUser thirdPartUser = thirdPartUserRepository.findByHashedKey(hashedKey);
        if (generalCheckingRepository.findById(accountId).isPresent()) {
            GeneralChecking senderAccount = generalCheckingRepository.findById(accountId).get();
            if(generalCheckingRepository.findBySecretKey(accountSecretKey).equals(senderAccount.getSecretKey())) {
                senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
            }
            throw new ResponseStatusException(HttpStatus.CONFLICT,"SecretKey is not correct");
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender Account do not exist");

    }
}
