package com.cps.claimdisbursementservice.service.impl;

import com.cps.claimdisbursementservice.dto.PolicyHolderBankDetailsDto;
import com.cps.claimdisbursementservice.entity.ClaimDisbursement;
import com.cps.claimdisbursementservice.repository.ClaimDisbursementRepository;
import com.cps.claimdisbursementservice.service.ClaimDibursementService;
import com.cps.claimdisbursementservice.service.PolicyHolderBankDetailsApiClient;
import com.cps.claimdisbursementservice.service.TrackingServiceApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ClaimDisbursementServiceImpl implements ClaimDibursementService {

    private final ClaimDisbursementRepository claimDisbursementRepository;
    private final PolicyHolderBankDetailsApiClient policyHolderBankDetailsApiClient;
    private final TrackingServiceApiClient trackingServiceApiClient;
    public ClaimDisbursement initiateClaim(String claimId){
        if(Objects.nonNull(claimId)){
            List<ClaimDisbursement> claims= claimDisbursementRepository.findAll();
            if(claims.stream().anyMatch(claim->claim.getClaimId().equals(claimId)))
                return claimDisbursementRepository.findByClaimId(claimId);
            ClaimDisbursement claimDisbursement = ClaimDisbursement.builder()
                    .claimId(claimId)
                    .disbursementStatus("Settlement Intiated")
                    .build();
            return claimDisbursementRepository.save(claimDisbursement);
        }
        return null;
    }

    public String getClaimDisbursementStatus(String claimId){
        if(Objects.nonNull(claimId)) {
            List<ClaimDisbursement> claims = claimDisbursementRepository.findAll();
            if (claims.stream().anyMatch(claim -> claim.getClaimId().equals(claimId)))
                return claimDisbursementRepository.findByClaimId(claimId).getDisbursementStatus();
            else
                return "No Disbursement Found For This Claim";
        }
        return "Please Enter Valid Claim Id";
    }

    public List<ClaimDisbursement> getAllClaimsToDisburse(){
        return claimDisbursementRepository.findAll();
    }
@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getServerDownMessage")
    public String settleClaim(String claimId, String status){
        if(Objects.nonNull(claimId)){
            List<ClaimDisbursement> claims= claimDisbursementRepository.findAll();
            if(claims.stream().anyMatch(claim->claim.getClaimId().equals(claimId))){
                PolicyHolderBankDetailsDto policyHolderBankDetailsDto = policyHolderBankDetailsApiClient.getPolicyHolderBankDetails(claimId);
                if(Objects.nonNull(policyHolderBankDetailsDto)) {
                    log.info("Complete Payment For following bank account details " +
                            policyHolderBankDetailsDto.getBankName() + " " +
                            policyHolderBankDetailsDto.getBankAccountNumber() + " " +
                            policyHolderBankDetailsDto.getIfscCode() + " " +
                            policyHolderBankDetailsDto.getAmount());
                    ClaimDisbursement claimDisbursement = claimDisbursementRepository.findByClaimId(claimId);
                    claimDisbursement.setDisbursementStatus(status);
                    claimDisbursementRepository.save(claimDisbursement);
                    trackingServiceApiClient.updateTracking(claimId,status);
                    return "Status Updated";
                }
                else {
                    return "Not Able To Fetch Bank Details";
                }
            }
            else{
                return "Not Disbursement Found for This Claim";
            }
        }
        return "Please Enter Valid Claim Id";
    }

    public String getServerDownMessage(Exception ex){
     return "OOPS Server is Down!!";
    }
}
