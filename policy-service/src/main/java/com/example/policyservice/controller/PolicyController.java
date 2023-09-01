package com.example.policyservice.controller;

import com.example.policyservice.dto.PolicyDto;
import com.example.policyservice.entity.Policy;
import com.example.policyservice.service.PolicyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/policies")
@AllArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping("/add")
    public ResponseEntity<PolicyDto> addPolicy(@RequestBody @Valid Policy policy) {
        return new ResponseEntity<>(policyService.addAndSavePolicy(policy), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PolicyDto>> getAll() {
        return ResponseEntity.ok(policyService.getAll());
    }

    @GetMapping("/get/byId/{id}")
    public ResponseEntity<PolicyDto> getByPolicyId(@PathVariable ("id") String policyId) {
        return ResponseEntity.ok(policyService.getPolicyById(policyId));
    }

    @DeleteMapping("/delete/byId/{id}")
    public ResponseEntity<String> deletePolicyById(@PathVariable ("id") String policyId) {
        return ResponseEntity.ok(policyService.removePolicyById(policyId));
    }
 }
