package com.example.policyservice.dto;

import com.example.policyservice.entity.Policy;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NotBlank
@Builder
public class PolicyHolderDto {
    private String policyHolderId;
    private String policyHolderName;
    private String dob;
    private String nomineeName;
    private String agentName;
    private Policy policyDetails;
}
