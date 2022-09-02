package com.example.demo.Models.Account;

import com.example.demo.Models.Enum.AccountStatus;
import com.example.demo.Models.User.AccountHolder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity


public class Saving extends GeneralChecking {



    public Saving() {
    }

    @NotNull
    private BigDecimal minimumDefaultBalance = BigDecimal.valueOf(1000); //Savings accounts should have a default minimumBalance of 1000
    @NotNull
    private BigDecimal defaultInterestRate = BigDecimal.valueOf(0.0025); //Savings accounts have a default interest rate of 0.0025

    private LocalDate lastInterestDate = LocalDate.now();


    public Saving(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, AccountStatus status, Long secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate, status, secretKey);
        setMinimumDefaultBalance(minimumBalance);
        setDefaultInterestRate(interestRate);
    }


    public BigDecimal getMinimumDefaultBalance() {
        return minimumDefaultBalance;
    }

    //Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
    public void setMinimumDefaultBalance(BigDecimal minimumBalance) {
        if(minimumBalance.compareTo(new BigDecimal(100)) > 0){
            this.minimumDefaultBalance = minimumBalance;
        } else{
            System.out.println("Minimal balance must not be lower than 100");
            this.minimumDefaultBalance = new BigDecimal(100);
        }
    }

    public BigDecimal getDefaultInterestRate() {
        return defaultInterestRate;
    }

    //Savings accounts may be instantiated with an interest rate other than the default,
    // with a maximum interest rate of 0.5
    public void setDefaultInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(new BigDecimal(0.5)) <=0 ){
            this.defaultInterestRate = interestRate;
        }else{
            System.out.println("Interest rate must not be higher than 0.5");
            this.defaultInterestRate = new BigDecimal(0.5);
        }
    }


    //If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically
    @Override
    public void setBalance(BigDecimal balance) {
        addInterestRate();
        if (balance.compareTo(BigDecimal.valueOf(250)) < 0) {
            super.setBalance(balance = minimumDefaultBalance.subtract(BigDecimal.valueOf(40)));
        } else{
            super.setBalance(balance);
        }
    }

    public void addInterestRate(){
        if (Period.between(lastInterestDate, LocalDate.now()).getYears() >= 1) {
           setBalance(getBalance().multiply(defaultInterestRate).multiply(BigDecimal.valueOf(Period.between(lastInterestDate, LocalDate.now()).getYears())).add(getBalance()));
            lastInterestDate.plusYears(Period.between(lastInterestDate, LocalDate.now()).getYears());
        }
    }

    @Override
    public BigDecimal getBalance() {
        addInterestRate();
        return super.getBalance();
    }

    public LocalDate getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(LocalDate lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }

}

