package com.bfs.claimservice.controller;

import com.bfs.claimservice.dto.BankDetailsResponseDto;
import com.bfs.claimservice.dto.DocumentVerificationDto;
import com.bfs.claimservice.exception.NoClaimDetailsFoundException;
import com.bfs.claimservice.model.ClaimForm;
import com.bfs.claimservice.service.ClaimFormService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/claims")
public class ClaimFormController {
    private ClaimFormService claimFormService;
    @PostMapping("/new-claim")
    public ResponseEntity<String> submitNewClaim(@RequestBody ClaimForm claimForm){
        return new ResponseEntity<String>(claimFormService.submitClaim(claimForm), HttpStatus.CREATED);

    }
    @GetMapping("/claim-status/{claimId}")
    public ResponseEntity<String> checkClaimStatus(@PathVariable String claimId) throws NoClaimDetailsFoundException {
        return new ResponseEntity<String>(claimFormService.checkClaimStatus(claimId),HttpStatus.OK);
    }
    @PostMapping(value = "upload-document/{claimId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<String> submitNewDocument(@RequestPart MultipartFile docImage, @PathVariable String claimId) throws IOException {

        String response = claimFormService.submitNewDocumentForClaim(docImage, claimId);
        if(response.equals("success")){
            return new ResponseEntity<String>("Document Verification is in progress....", HttpStatus.OK);
        }
        return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
}
@GetMapping("/doc-status/{claimId}")
public ResponseEntity<String> checkYourDocumentStatus(@PathVariable String claimId){
        return new ResponseEntity<>(claimFormService.getVerificationStatus(claimId),HttpStatus.OK);
}
@GetMapping("/track-status/{trackId}")
public ResponseEntity<String> checkTrackingStatus(@PathVariable Long trackId){
        return new ResponseEntity<>(claimFormService.getTrackingStatus(trackId),HttpStatus.OK);
}
@GetMapping("/bank/details/get/{claimId}")
public ResponseEntity<BankDetailsResponseDto> getBankDetailsOfPolicyHolder(@PathVariable String claimId){
        return new ResponseEntity<BankDetailsResponseDto>(claimFormService.getBankDetailsOfPolicyHolder(claimId),HttpStatus.OK);
}
}

