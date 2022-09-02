package com.example.demo.Models.Account;

import com.example.demo.Models.Enum.AccountStatus;
import com.example.demo.Models.User.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)

    public class GeneralChecking extends Account {


    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private Long secretKey;

    public GeneralChecking() {
    }

    public GeneralChecking(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, LocalDate creationDate, AccountStatus status, Long secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee);
        this.creationDate = creationDate;
        this.status = status;
        this.secretKey = secretKey;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Long getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Long secretKey) {
        this.secretKey = secretKey;
    }
}
