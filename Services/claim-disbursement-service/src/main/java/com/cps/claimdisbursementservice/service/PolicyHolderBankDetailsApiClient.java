package com.cps.claimdisbursementservice.service;

import com.cps.claimdisbursementservice.dto.PolicyHolderBankDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLAIM-SERVICE")
public interface PolicyHolderBankDetailsApiClient {
    @GetMapping("api/claims/bank/details/get/{claimId}")
    PolicyHolderBankDetailsDto getPolicyHolderBankDetails(@PathVariable("claimId") String claimId);
}
