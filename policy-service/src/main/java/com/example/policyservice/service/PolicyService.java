package com.example.policyservice.service;

import com.example.policyservice.dto.PolicyDto;
import com.example.policyservice.entity.Policy;

import java.util.List;

public interface PolicyService {
    PolicyDto addAndSavePolicy(Policy policy);

    List<PolicyDto> getAll();

    PolicyDto getPolicyById(String policyId);

    String removePolicyById(String policyId);
}
