package com.example.demo.Controller.Interfaces;

import java.math.BigDecimal;

public interface ThirdPartUserInterface {

    public void sendMoney(String hashedKey, Long accountId, BigDecimal amount, Long accountSecretKey);
    public void receiveMoney(String hashedKey, Long accountId, BigDecimal amount, Long accountSecretKey);
}
