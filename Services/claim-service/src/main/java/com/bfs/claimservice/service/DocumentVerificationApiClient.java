package com.bfs.claimservice.service;

import com.bfs.claimservice.dto.DocumentVerificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "DOCUMENT-VERIFICATION-SERVICE")
public interface DocumentVerificationApiClient {
    @PostMapping("api/document/verify")
    DocumentVerificationDto verifyNewDocument(@RequestBody DocumentVerificationDto documentVerificationDto)throws IOException;
    @GetMapping("api/document/status/{claimId}")
    String getVerificationStatus(@PathVariable String claimId);
}
