package com.cps.documentverificationservice.controller;

import com.cps.documentverificationservice.entity.DocumentVerification;
import com.cps.documentverificationservice.service.DocumentVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/document")
public class DocumentVerificationController {
    private final DocumentVerificationService documentVerificationService;
//
//    @PostMapping(path="/verify", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<?> submitDocumentForVerification(@RequestPart("data") DocumentVerification documentVerify, @RequestPart("docImage") MultipartFile docImage) throws IOException {
//        DocumentVerification document = documentVerificationService.saveDocumentForVerification(docImage, documentVerify.getClaimId());
//        if(Objects.nonNull(document)){
//            return new ResponseEntity<DocumentVerification>(document, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<String>("Please Provide Valid Data for Document Verification", HttpStatus.BAD_REQUEST);
//    }

  @PostMapping("/verify")
    public ResponseEntity<?> submitDocumentForVerification(@RequestBody DocumentVerification documentVerify) throws IOException {
        DocumentVerification document = documentVerificationService.saveDocumentForVerification(documentVerify.getDocumentImage(), documentVerify.getClaimId());
        if(Objects.nonNull(document)){
            return new ResponseEntity<DocumentVerification>(document, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Please Provide Valid Data for Document Verification", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/status/{claimId}")
    public ResponseEntity<?> getDocumentVerificationStatus(@PathVariable String claimId){
        return new ResponseEntity<String>(documentVerificationService.getDocumentVerificationStatus(claimId), HttpStatus.OK);
    }

    @GetMapping(path="/getAllDocumentsToVerify", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllDocumentsToVerify(){
        return new ResponseEntity<List<DocumentVerification>>(documentVerificationService.getAllDocumentsToVerify(), HttpStatus.OK);
    }

    @PutMapping("updateStatus/{claimId}")
    public ResponseEntity<?> updateStatus(@PathVariable String claimId, @RequestParam("status") String status){
        DocumentVerification updatedDocument = documentVerificationService.updateDocumentVerificationStatus(claimId, status);
        if(Objects.nonNull(updatedDocument))
            return new ResponseEntity<DocumentVerification>(updatedDocument, HttpStatus.OK);
        return new ResponseEntity<String>("Documents Not Found for this claim", HttpStatus.BAD_REQUEST);
    }

}
