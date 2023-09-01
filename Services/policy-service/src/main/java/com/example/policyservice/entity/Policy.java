package com.example.policyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy {
    @Id
    private String policyId;
    @NotBlank(message = "Provide valid policy name.!!")
    private String policyName;
    @NotBlank(message = "Provide valid policy period.!!")
    private String policyPeriod;
    @NotBlank(message = "Provide valid policy type.!!")
    private String policyType;
    @NotNull(message = "Provide valid policy premium amount.!!")
    private Double premiumAmount;
    @NotNull(message = "Provide valid policy covered amount.!!")
    private Double coveredAmount;
}
