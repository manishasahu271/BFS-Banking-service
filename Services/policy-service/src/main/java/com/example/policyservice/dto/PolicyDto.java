package com.example.policyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDto {
    private String policyId;
    private String policyName;
    private String policyPeriod;
    private String policyType;
    private Double premiumAmount;
    private Double coveredAmount;
}
