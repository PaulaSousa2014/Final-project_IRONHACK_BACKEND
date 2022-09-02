package com.example.demo.Models.User;


import com.example.demo.Models.Account.Account;
import com.example.demo.Models.User.Address;
import com.example.demo.Models.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity

public class AccountHolder extends User {


    @OneToMany (mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccountList = new ArrayList<>();
    @OneToMany (mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccountList = new ArrayList<>();

    private LocalDate dateOfBirth;

    @Embedded
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="address", column = @Column(name = "secondaryAddress")),
            @AttributeOverride(name="postalCode", column = @Column(name = "secondaryPostalCode")),
            @AttributeOverride(name="city", column = @Column(name = "secondaryCity")),
            @AttributeOverride(name= "country",column = @Column(name = "secondaryCountry"))
    })
    private Address secondaryAddress;


    public AccountHolder() {
    }

    public AccountHolder(String name, String password, List<Account> primaryAccountList, List<Account> secondaryAccountList, LocalDate dateOfBirth, Address primaryAddress, Address secondaryAddress) {
        super(name, password);
        this.primaryAccountList = primaryAccountList;
        this.secondaryAccountList = secondaryAccountList;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.secondaryAddress = secondaryAddress;
    }

    public List<Account> getPrimaryAccountList() {
        return primaryAccountList;
    }

    public void setPrimaryAccountList(List<Account> primaryAccountList) {
        this.primaryAccountList = primaryAccountList;
    }

    public List<Account> getSecondaryAccountList() {
        return secondaryAccountList;
    }

    public void setSecondaryAccountList(List<Account> secondaryAccountList) {
        this.secondaryAccountList = secondaryAccountList;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
}
