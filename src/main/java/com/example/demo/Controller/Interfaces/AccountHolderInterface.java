package com.example.demo.Controller.Interfaces;

import java.math.BigDecimal;

public interface AccountHolderInterface {

    public BigDecimal accessBalance(Long accountId, Long userId);
    public void transferMoney(String senderName, Long accountId, BigDecimal amount, Long receiverAccountId);

}
