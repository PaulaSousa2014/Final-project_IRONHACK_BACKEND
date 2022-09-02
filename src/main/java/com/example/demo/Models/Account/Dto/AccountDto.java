package com.example.demo.Models.Account.Dto;

import com.example.demo.Models.Enum.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AccountDto {

    private BigDecimal balanceDto;
    private Long primaryOwnerDto;
    private Long secondaryOwnerDto;
    private BigDecimal penaltyFeeDto;
    private BigDecimal creditLimitDto;
    private BigDecimal interestRateDto;
    private LocalDate creationDateDto;
    private AccountStatus statusDto;
    private Long secretKeyDto;
    private BigDecimal minimumBalanceDto;
    private BigDecimal monthlyMaintenanceFeeDto;
    private LocalDate lastAddedInterestRateDto;



}
