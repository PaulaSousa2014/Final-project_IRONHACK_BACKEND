package com.example.demo.Services;

import com.example.demo.Models.Account.*;
import com.example.demo.Models.Account.Dto.AccountDto;
import com.example.demo.Models.User.*;
import com.example.demo.Repositories.Account.*;
import com.example.demo.Repositories.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ThirdPartUserRepository thirdPartUserRepository;



    // Method to add roles:
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


// For creating new Checking Account when primary owner is older than 24 years old:

    public Account addCheckingAccount(AccountDto newCheckingAccount) {

        if (accountHolderRepository.findById(newCheckingAccount.getPrimaryOwnerDto()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(newCheckingAccount.getPrimaryOwnerDto()).get();
            AccountHolder secondaryOwner = null;

            if (newCheckingAccount.getSecondaryOwnerDto() != null && accountHolderRepository.findById(newCheckingAccount.getSecondaryOwnerDto()).isPresent()) {
                secondaryOwner = accountHolderRepository.findById(newCheckingAccount.getSecondaryOwnerDto()).get();
            }

            Integer age = Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears();


            StudentChecking newAccount;
            if (age > 24) {

                Checking checkingAccount = new Checking(
                        newCheckingAccount.getBalanceDto(),
                        primaryOwner,
                        secondaryOwner,
                        newCheckingAccount.getPenaltyFeeDto(),
                        newCheckingAccount.getCreationDateDto(),
                        newCheckingAccount.getStatusDto(),
                        newCheckingAccount.getSecretKeyDto()
                );

                return checkingRepository.save(checkingAccount);

            } else {
                System.out.println("To create a Checking account, primary owner must have more than 24 years old. " +
                        "For younger primary owner, create a Student Checking Account.");

                newAccount = new StudentChecking(
                        newCheckingAccount.getBalanceDto(),
                        primaryOwner,
                        secondaryOwner,
                        newCheckingAccount.getPenaltyFeeDto(),
                        newCheckingAccount.getCreationDateDto(),
                        newCheckingAccount.getStatusDto(),
                        newCheckingAccount.getSecretKeyDto());

            }
            return studentCheckingRepository.save(newAccount);
        }
        throw new IllegalArgumentException("Primary Owner does not exist");
    }


// For creating a saving Account:

    public Saving addSavingAccount(AccountDto newSavingAccount) {

        if (accountHolderRepository.findById(newSavingAccount.getPrimaryOwnerDto()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(newSavingAccount.getPrimaryOwnerDto()).get();
            AccountHolder secondaryOwner = null;

            if (newSavingAccount.getSecondaryOwnerDto() != null && accountHolderRepository.findById(newSavingAccount.getSecondaryOwnerDto()).isPresent()) {
                secondaryOwner = accountHolderRepository.findById(newSavingAccount.getSecondaryOwnerDto()).get();
            }

            Saving savingAccount = new Saving(newSavingAccount.getBalanceDto(),
                    primaryOwner,
                    secondaryOwner,
                    newSavingAccount.getPenaltyFeeDto(),
                    newSavingAccount.getCreationDateDto(),
                    newSavingAccount.getStatusDto(),
                    newSavingAccount.getSecretKeyDto(),
                    newSavingAccount.getMinimumBalanceDto(),
                    newSavingAccount.getInterestRateDto());
            return savingRepository.save(savingAccount);
        }
        throw new IllegalArgumentException("Primary account holder does not exist");
    }

    // For creating a CreditCard Account:

    public CreditCard addCreditCardAccount(AccountDto newCreditCardAccount) {
        if (accountHolderRepository.findById(newCreditCardAccount.getPrimaryOwnerDto()).isPresent()) {
            AccountHolder primaryOwner = accountHolderRepository.findById(newCreditCardAccount.getPrimaryOwnerDto()).get();
            AccountHolder secondaryOwner = null;

            if (newCreditCardAccount.getSecondaryOwnerDto() != null && accountHolderRepository.findById(newCreditCardAccount.getSecondaryOwnerDto()).isPresent()) {
                secondaryOwner = accountHolderRepository.findById(newCreditCardAccount.getSecondaryOwnerDto()).get();
            }

                CreditCard creditCardAccount = new CreditCard(
                        newCreditCardAccount.getBalanceDto(),
                        primaryOwner,
                        secondaryOwner,
                        newCreditCardAccount.getPenaltyFeeDto(),
                        newCreditCardAccount.getCreditLimitDto(),
                        newCreditCardAccount.getInterestRateDto(),
                        newCreditCardAccount.getCreationDateDto(),
                        newCreditCardAccount.getLastAddedInterestRateDto()
                );

                return creditCardRepository.save(creditCardAccount);
        }
        throw new IllegalArgumentException("Account holder does not exist");
    }


    //Method to access the balance for any account

    public BigDecimal getBalance(Long accountId) {
        if (accountRepository.findById(accountId).isPresent()) {
            Account clientAccount = accountRepository.findById(accountId).get();
            accountRepository.save(clientAccount);
            return clientAccount.getBalance();
        }
       throw  new IllegalArgumentException("Account does not exist");
    }


    //Method to modify the balance for any account

    public BigDecimal setBalance(Long accountId,BigDecimal balance){
        if (accountRepository.findById(accountId).isPresent()){
            Account clientAccount = accountRepository.findById(accountId).get();
            clientAccount.setBalance(balance);
            accountRepository.save(clientAccount);
            return balance;
        }
        throw new IllegalArgumentException("Account does not exist");
    }




//Delete account

    public void deleteAccount(Long accountId){
        if (accountRepository.findById(accountId).isPresent()) {
            accountRepository.deleteById(accountId);
        }
        throw new IllegalArgumentException("Account does not exist");
    }



}

