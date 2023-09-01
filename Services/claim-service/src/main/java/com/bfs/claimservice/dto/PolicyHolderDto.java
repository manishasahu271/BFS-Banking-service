package com.bfs.claimservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyHolderDto {
    private String policyHolderId;
    private String policyHolderName;
    private String dob;
    private String nomineeName;
    private String agentName;
    private String policyId;
}
