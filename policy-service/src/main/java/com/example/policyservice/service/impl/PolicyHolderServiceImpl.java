package com.example.policyservice.service.impl;

import com.example.policyservice.dto.PolicyHolderDto;
import com.example.policyservice.entity.PolicyHolder;
import com.example.policyservice.exception.GlobalExceptionHandler;
import com.example.policyservice.repository.PolicyHolderRepository;
import com.example.policyservice.repository.PolicyRepository;
import com.example.policyservice.service.PolicyHolderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PolicyHolderServiceImpl implements PolicyHolderService {
    private final PolicyHolderRepository policyHolderRepository;
    private final PolicyRepository policyRepository;
    @Override
    public PolicyHolderDto addAndSaveHolder(PolicyHolder policyHolder, String policyId) {
        log.info("Inside addAndSaveHolder");
        policyHolder.setPolicyHolderId(UUID.randomUUID().toString());
        policyHolder.setPolicyId(policyId);
        if(policyHolder.getAgentName().isEmpty()) {
            policyHolder.setAgentName("No Agent for policy holder");
        }
        return mapToDto(policyHolderRepository.save(policyHolder));
    }

    @Override
    public PolicyHolderDto getPolicyHolderById(String policyHolderId) {
        log.info("Inside getPolicyHolderById");
        PolicyHolder policyHolder = policyHolderRepository.findById(policyHolderId).orElseThrow(() -> new GlobalExceptionHandler(
                String.format("Policy holder with id: %s does not exists..!!", policyHolderId)
        ));
        return mapToDto(policyHolder);
    }

    @Override
    public List<PolicyHolderDto> getAll() {
        log.info("Inside getAll");
        return policyHolderRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PolicyHolderDto mapToDto(PolicyHolder policyHolder) {
        return PolicyHolderDto.builder()
                .policyHolderId(policyHolder.getPolicyHolderId())
                .policyHolderName(policyHolder.getPolicyHolderName())
                .nomineeName(policyHolder.getNomineeName())
                .agentName(policyHolder.getAgentName())
                .dob(policyHolder.getDob())
                .policyDetails(policyRepository.findById(policyHolder.getPolicyId()).orElseThrow(() -> new GlobalExceptionHandler(
                        String.format("Policy with id: %s does not exists!!", policyHolder.getPolicyId())
                )))
                .build();
    }
}
