package com.bfs.claimservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentVerificationDto {
    private byte[] documentImage;
    private String claimId;
}
