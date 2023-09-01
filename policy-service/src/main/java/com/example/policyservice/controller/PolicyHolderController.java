package com.example.policyservice.controller;

import com.example.policyservice.dto.PolicyHolderDto;
import com.example.policyservice.entity.PolicyHolder;
import com.example.policyservice.service.PolicyHolderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/holder/details")
public class PolicyHolderController {
    private final PolicyHolderService policyHolderService;

    @PostMapping("/add/policy/{id}")
    public ResponseEntity<PolicyHolderDto> addPolicyHolder(@RequestBody @Valid PolicyHolder policyHolder, @PathVariable("id") String policyId) {
        return new ResponseEntity<>(policyHolderService.addAndSaveHolder(policyHolder, policyId), HttpStatus.CREATED);
    }

    @GetMapping("/get/byId/{id}")
    public ResponseEntity<PolicyHolderDto> getPolicyHolderDetailsById(@PathVariable ("id") String policyHolderId) {
        return ResponseEntity.ok(policyHolderService.getPolicyHolderById(policyHolderId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PolicyHolderDto>> getAll() {
        return ResponseEntity.ok(policyHolderService.getAll());
    }
}
