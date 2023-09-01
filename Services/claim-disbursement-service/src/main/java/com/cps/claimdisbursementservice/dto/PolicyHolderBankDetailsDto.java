package com.cps.claimdisbursementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyHolderBankDetailsDto {
    private String bankName;
    private String bankAccountNumber;
    private String ifscCode;
    private double amount;
}
