package com.example.demo.Models.Account;

import com.example.demo.Models.User.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)





public class CreditCard extends Account {


    @NotNull
    private BigDecimal creditLimit = BigDecimal.valueOf(100); //CreditCard accounts have a default creditLimit of 100
    @NotNull
    private BigDecimal interestRate = BigDecimal.valueOf(0.2); //CreditCards have a default interestRate of 0.2
    @NotNull
    private LocalDate creditCardCreationDate;
    private LocalDate lastAddedInterest = LocalDate.now();


    public CreditCard() {
    }

    public CreditCard(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate, LocalDate creditCardCreationDate, LocalDate lastAddedInterest) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee);
        setCreditLimit(creditLimit);
        this.interestRate = interestRate;
        this.creditCardCreationDate = creditCardCreationDate;
        this.lastAddedInterest = lastAddedInterest;
    }

    public LocalDate getLastAddedInterest() {
        return lastAddedInterest;
    }

    public void setLastAddedInterest(LocalDate lastAddedInterest) {
        this.lastAddedInterest = lastAddedInterest;
    }

    public LocalDate getCreditCardCreationDate() {
        return creditCardCreationDate;
    }

    public void setCreditCardCreationDate(LocalDate creditCardCreationDate) {
        this.creditCardCreationDate = creditCardCreationDate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    //CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
    public void setCreditLimit(BigDecimal creditLimit) {
        if(creditLimit.compareTo(BigDecimal.valueOf(100000)) < 0){
             if (creditLimit.compareTo(BigDecimal.valueOf(100)) >= 0){
                this.creditLimit = creditLimit;
            }else{
                 System.out.println("Credit limit can not be higher than 100000");
                 this.creditLimit= BigDecimal.valueOf(100000);
            }
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    //CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1
    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(BigDecimal.valueOf(0.1)) < 0){
            this.interestRate= interestRate;
        } else{
            System.out.println("Interest rate can not be lower than 0.1");
            this.interestRate = BigDecimal.valueOf(0.1);
        }
    }

    //Method to add monthly interest rate on Credit card account

    public void addInterestRate(){

            if(Period.between(lastAddedInterest, LocalDate.now()).getMonths() >= 1){
                setBalance(getBalance().multiply(getInterestRate().divide(BigDecimal.valueOf(12)).multiply(BigDecimal.valueOf(Period.between(lastAddedInterest, LocalDate.now()).getMonths())).add(getBalance())));
                lastAddedInterest.plusMonths(Period.between(lastAddedInterest, LocalDate.now()).getMonths());

            }

        }

    @Override
    public BigDecimal getBalance() {
        addInterestRate();
        return super.getBalance();
    }

    @Override
    public void setBalance(BigDecimal balance) {
        addInterestRate();
        super.setBalance(balance);
    }
}



