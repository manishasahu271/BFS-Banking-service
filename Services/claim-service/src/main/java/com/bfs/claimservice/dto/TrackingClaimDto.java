package com.bfs.claimservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrackingClaimDto {
    private Long trackingId;
    private String claimId;
    private String claimStatus;
}
