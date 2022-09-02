package com.example.demo.Models.Account;

import com.example.demo.Models.Enum.AccountStatus;
import com.example.demo.Models.User.AccountHolder;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

public class StudentChecking extends GeneralChecking {

    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, AccountStatus status, Long secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate, status, secretKey);
    }

}
