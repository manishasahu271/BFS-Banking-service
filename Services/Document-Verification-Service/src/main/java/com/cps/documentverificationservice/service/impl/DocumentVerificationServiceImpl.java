package com.cps.documentverificationservice.service.impl;

import com.cps.documentverificationservice.entity.DocumentVerification;
import com.cps.documentverificationservice.repository.DocumentVerificationRepository;
import com.cps.documentverificationservice.service.ClaimDisbursementApiClient;
import com.cps.documentverificationservice.service.DocumentVerificationService;
import com.cps.documentverificationservice.service.TrackingDocApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class DocumentVerificationServiceImpl implements DocumentVerificationService {

    private final DocumentVerificationRepository documentVerificationRepository;
    private final TrackingDocApiClient trackingDocApiClient;
    private final ClaimDisbursementApiClient claimDisbursementApiClient;
    public DocumentVerification saveDocumentForVerification(byte[] docImage, String claimId) throws IOException {
        if(Objects.nonNull(claimId)){
            List<DocumentVerification> documents= documentVerificationRepository.findAll();
            if(documents.stream().anyMatch(doc->doc.getClaimId().equals(claimId)))
                return documentVerificationRepository.findByClaimId(claimId);
            if(Objects.nonNull(docImage)) {
                DocumentVerification document = DocumentVerification.builder()
                        .documentImage(docImage)
                        .claimId(claimId)
                        .status("Pending...")
                        .build();
                return documentVerificationRepository.save(document);
            }
        }
        return null;
    }

    public String getDocumentVerificationStatus(String claimId){
        List<DocumentVerification> documents= documentVerificationRepository.findAll();
        if(documents.stream().anyMatch(doc->doc.getClaimId().equals(claimId)))
            return documentVerificationRepository.findByClaimId(claimId).getStatus();
        return "No Claim Found with this Claim Id";
    }

    public List<DocumentVerification> getAllDocumentsToVerify(){
        List<DocumentVerification> documents = documentVerificationRepository.findAll();
//        documents.forEach((doc)->{
//            doc.setDocumentImage(Base64.getEncoder().encode(doc.getDocumentImage()));
//        });
        return documents;
    }
@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getTrackingErrorMessage")
    public DocumentVerification updateDocumentVerificationStatus(String claimId, String status){
        List<DocumentVerification> documents= documentVerificationRepository.findAll();
        if(documents.stream().anyMatch(doc->doc.getClaimId().equals(claimId))) {
            DocumentVerification document = documentVerificationRepository.findByClaimId(claimId);
            document.setStatus(status);
            documentVerificationRepository.save(document);
            trackingDocApiClient.updateTracking(claimId,status);
            claimDisbursementApiClient.initiateClaimDisbursement(claimId);
            return document;
        }
        return null;
    }
    public String getTrackingErrorMessage(Exception ex){
        return "OOPS Tracking Server is Down!!";
    }
}
