package com.example.policyservice.service.impl;

import com.example.policyservice.dto.PolicyDto;
import com.example.policyservice.entity.Policy;
import com.example.policyservice.exception.GlobalExceptionHandler;
import com.example.policyservice.repository.PolicyRepository;
import com.example.policyservice.service.PolicyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    @Override
    public PolicyDto addAndSavePolicy(Policy policy) {
        log.info("Inside addAndSavePolicy");
        policy.setPolicyId(UUID.randomUUID().toString());
        if(policyRepository.findByPolicyName(policy.getPolicyName()).isPresent()) {
            throw new GlobalExceptionHandler(String.format("Policy with the name: %s already exists", policy.getPolicyName()));
        }
        return mapToDto(policyRepository.save(policy));
    }

    @Override
    public List<PolicyDto> getAll() {
        log.info("Inside getAll");
        return policyRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyDto getPolicyById(String policyId) {
        log.info("Inside getPolicyById");
        Policy policy = policyRepository.findById(policyId).orElseThrow(()-> new GlobalExceptionHandler(
                String.format("Policy with the id: %s does not exists..!!",policyId)
        ));
        return mapToDto(policy);
    }

    @Override
    public String removePolicyById(String policyId) {
        log.info("Inside removePolicyById");
        if(policyRepository.findById(policyId).isPresent()) {
            policyRepository.deleteById(policyId);
            return "Policy deleted successfully!!";
        } else  {
         throw new GlobalExceptionHandler(String.format("Policy does not exists with the id: %s", policyId));
        }
    }

    private PolicyDto mapToDto(Policy policy) {
        return PolicyDto.builder()
                .policyId(policy.getPolicyId())
                .policyName(policy.getPolicyName())
                .policyPeriod(policy.getPolicyPeriod())
                .policyType(policy.getPolicyType())
                .coveredAmount(policy.getCoveredAmount())
                .premiumAmount(policy.getPremiumAmount())
                .build();
    }
}
