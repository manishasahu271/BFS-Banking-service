package com.bfs.claimservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claims")
public class ClaimForm {
    @Id
    private String claimId;

    @Column
    private String claimType;
    @Column(name = "policy_holder_id")
    private String policyHolderId;
    @Column(name = "policy_holder_status")
    private String policyHolderDetails;
    @Column(name = "claim_condition")
    private String claimCondition;
    @Column
    private String yearOfClaim;

    @Column(name = "claim_validity")
    private String claimValidity;
    @Column(name = "claim_status")
    private String claimStatus;
    @Column
    private String bankName;
    @Column
    private String bankAccountNumber;
    @Column
    private String ifscCode;
    @Column
    private double amount;
}
