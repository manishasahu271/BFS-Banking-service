package com.cps.claimdisbursementservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClaimDisbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long disbursementId;
    private String claimId;
    private String disbursementStatus="Pending...";
}
