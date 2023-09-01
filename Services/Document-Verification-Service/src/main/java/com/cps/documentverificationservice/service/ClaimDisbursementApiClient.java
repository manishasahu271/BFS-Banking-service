package com.cps.documentverificationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "CLAIM-DISBURSEMENT-SERVICE")
public interface ClaimDisbursementApiClient {
    @PostMapping("api/claim/disburse/initiate/{claimId}")
    String initiateClaimDisbursement(@PathVariable String claimId);
}
