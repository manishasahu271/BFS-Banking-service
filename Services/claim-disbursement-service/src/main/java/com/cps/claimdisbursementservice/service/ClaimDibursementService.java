package com.cps.claimdisbursementservice.service;

import com.cps.claimdisbursementservice.entity.ClaimDisbursement;

import java.util.List;

public interface ClaimDibursementService {
    public ClaimDisbursement initiateClaim(String claimId);

    public String settleClaim(String claimId, String status);
    public List<ClaimDisbursement> getAllClaimsToDisburse();
    public String getClaimDisbursementStatus(String claimId);
}
