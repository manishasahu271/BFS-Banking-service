package com.cps.documentverificationservice.service;

import com.cps.documentverificationservice.entity.DocumentVerification;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentVerificationService {
    DocumentVerification saveDocumentForVerification(byte[] docImage, String claimId)  throws IOException;
    String getDocumentVerificationStatus(String claimId);
    List<DocumentVerification> getAllDocumentsToVerify();
    DocumentVerification updateDocumentVerificationStatus(String claimId, String status);
}
