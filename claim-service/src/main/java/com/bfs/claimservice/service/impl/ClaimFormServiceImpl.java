package com.bfs.claimservice.service.impl;

import com.bfs.claimservice.dto.BankDetailsResponseDto;
import com.bfs.claimservice.dto.DocumentVerificationDto;
import com.bfs.claimservice.dto.PolicyHolderDto;
import com.bfs.claimservice.dto.TrackingClaimDto;
import com.bfs.claimservice.exception.NoClaimDetailsFoundException;
import com.bfs.claimservice.model.ClaimForm;
import com.bfs.claimservice.repository.ClaimFormRepository;
import com.bfs.claimservice.service.ClaimFormService;
import com.bfs.claimservice.service.DocumentVerificationApiClient;
import com.bfs.claimservice.service.PolicyHolderApiClient;
import com.bfs.claimservice.service.TrackingClaimApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClaimFormServiceImpl implements ClaimFormService {
    private ClaimFormRepository claimFormRepository;
    private PolicyHolderApiClient policyHolderApiClient;
    private DocumentVerificationApiClient documentVerificationApiClient;
    private TrackingClaimApiClient trackingClaimApiClient;
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getServerDownMessage")
    @Override
    public String submitClaim(ClaimForm claimForm) {
        long currTime=System.currentTimeMillis();
        String claimId=String.valueOf(currTime).substring(7,13);
        claimId="CL"+claimId;
        claimForm.setClaimId(claimId);
        claimForm.setClaimStatus("Pending");
        claimForm.setPolicyHolderDetails("To be verified");
        //coming from policy-service
        PolicyHolderDto policyHolderDto=policyHolderApiClient.getPolicyHolderDetails(claimForm.getPolicyHolderId());
        if(policyHolderDto!=null){
            claimForm.setPolicyHolderDetails("Verified");

        }
        claimFormRepository.save(claimForm);
         return "Claim Approval Pending";
    }

    @Override
    public String checkClaimStatus(String claimId) throws NoClaimDetailsFoundException {
        ClaimForm claimForm=claimFormRepository.findById(claimId).orElseThrow(
                ()->new NoClaimDetailsFoundException("No Claim details found!!")
        );
     return claimForm.getClaimStatus();
    }


    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDepartmentErrorMessage")
    public String submitNewDocumentForClaim(MultipartFile docImage, String claimId) throws IOException {
        if(Objects.nonNull(claimFormRepository.findById(claimId))){
            if(!docImage.isEmpty()){
                DocumentVerificationDto documentVerificationDto = DocumentVerificationDto.builder()
                        .documentImage(docImage.getBytes())
                        .claimId(claimId)
                        .build();
                //Send to feignClient
                documentVerificationApiClient.verifyNewDocument(documentVerificationDto);
                TrackingClaimDto trackingClaimDto=new TrackingClaimDto();
                trackingClaimDto.setClaimId(claimId);
                trackingClaimDto.setClaimStatus("Document Verification Pending....");
                trackingClaimApiClient.createNewTrackClaim(trackingClaimDto);

                return "success";
            }
            else{
                return "Please upload valid document";
            }
        }
        else{
            return "Claim Not Found";
        }
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDepartmentErrorMessage")
    public String getVerificationStatus(String claimId) {
        String status=documentVerificationApiClient.getVerificationStatus(claimId);
        return status;
    }
    @Override
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getTrackingErrorMessage")
    public String getTrackingStatus(Long trackingId){
        String status=trackingClaimApiClient.checkTracking(trackingId);
        return status;
    }

    @Override
    public BankDetailsResponseDto getBankDetailsOfPolicyHolder(String claimId) {
        ClaimForm claimForm=claimFormRepository.findById(claimId).orElse(null);
        if(Objects.nonNull(claimForm)){
            BankDetailsResponseDto bankDetailsResponseDto=BankDetailsResponseDto.builder().bankName(claimForm.getBankName())
                    .bankAccountNumber(claimForm.getBankAccountNumber())
                    .amount(claimForm.getAmount())
                    .ifscCode(claimForm.getIfscCode()).build();
            return bankDetailsResponseDto;
        }
        return null;
    }
    public String getServerDownMessage(Exception ex){
        return "OOPS Server Is Down!!";
    }
    public String getDepartmentErrorMessage(Exception ex){return "OOPS Department Server is Down!!";}
    public String getTrackingErrorMessage(Exception ex){return "OOPS Tracking Server is Down!!";}
}
