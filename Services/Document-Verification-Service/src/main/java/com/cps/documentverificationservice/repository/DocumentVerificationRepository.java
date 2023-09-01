package com.cps.documentverificationservice.repository;

import com.cps.documentverificationservice.entity.DocumentVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentVerificationRepository extends JpaRepository<DocumentVerification, Long> {
    DocumentVerification findByClaimId(String claimId);
}
