package com.example.policyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PolicyHolder {
    @Id
    private String policyHolderId;
    @NotBlank(message = "Please provide valid policy holder name")
    private String policyHolderName;
    @NotBlank(message = "Please provide valid date of birth")
    private String dob;
    @NotBlank(message = "Please provide valid nominee name")
    private String nomineeName;
    private String agentName;
    private String policyId;
}
