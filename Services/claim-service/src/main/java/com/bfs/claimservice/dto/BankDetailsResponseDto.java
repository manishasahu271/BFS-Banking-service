package com.bfs.claimservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetailsResponseDto {
    private String bankName;
    private String bankAccountNumber;
    private String ifscCode;
    private double amount;
}
