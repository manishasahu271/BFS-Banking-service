package com.example.policyservice.service;

import com.example.policyservice.dto.PolicyHolderDto;
import com.example.policyservice.entity.PolicyHolder;

import java.util.List;

public interface PolicyHolderService {
    PolicyHolderDto addAndSaveHolder(PolicyHolder policyHolder, String policyId);

    PolicyHolderDto getPolicyHolderById(String policyHolderId);

    List<PolicyHolderDto> getAll();
}
