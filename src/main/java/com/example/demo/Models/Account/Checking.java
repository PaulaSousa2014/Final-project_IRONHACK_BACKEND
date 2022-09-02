package com.example.demo.Models.Account;

import com.example.demo.Models.Enum.AccountStatus;
import com.example.demo.Models.User.AccountHolder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity



public class Checking extends GeneralChecking {
    @NotNull
    private final BigDecimal minimumBalance = BigDecimal.valueOf(250);//Checking accounts should have a minimumBalance of 250
    @NotNull
    private final BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);//Checking accounts should have a monthlyMaintenanceFee of 12


    public Checking() {
    }

    public Checking(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, AccountStatus status, Long secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate, status, secretKey);

    }


    //If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically

    @Override
    public void setBalance(BigDecimal balance) {
        if (balance.compareTo(BigDecimal.valueOf(250)) < 0) {
            super.setBalance(balance = minimumBalance.subtract(BigDecimal.valueOf(40)));
        } else{
            super.setBalance(balance);
        }
    }


    public BigDecimal getMinimumBalance () {
        return minimumBalance;
    }


    public BigDecimal getMonthlyMaintenanceFee () {
        return monthlyMaintenanceFee;
    }
}
