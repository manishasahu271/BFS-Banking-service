package com.cps.claimdisbursementservice.repository;

import com.cps.claimdisbursementservice.entity.ClaimDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimDisbursementRepository extends JpaRepository<ClaimDisbursement, Long> {
    ClaimDisbursement findByClaimId(String claimId);
}
