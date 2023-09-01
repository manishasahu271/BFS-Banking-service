package com.bfs.claimservice.service;

import com.bfs.claimservice.dto.BankDetailsResponseDto;
import com.bfs.claimservice.dto.DocumentVerificationDto;
import com.bfs.claimservice.exception.NoClaimDetailsFoundException;
import com.bfs.claimservice.model.ClaimForm;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ClaimFormService {
    public String submitClaim(ClaimForm claimForm);
    public String checkClaimStatus(String claimId) throws NoClaimDetailsFoundException;
    public String submitNewDocumentForClaim(MultipartFile docImage, String claimId) throws IOException;
    public String getVerificationStatus(String claimId);
    public String getTrackingStatus(Long trackId);
   public BankDetailsResponseDto getBankDetailsOfPolicyHolder(String claimId);

}
