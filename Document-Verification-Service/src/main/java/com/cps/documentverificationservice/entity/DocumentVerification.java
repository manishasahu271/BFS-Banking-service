package com.cps.documentverificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docVerificationService")
public class DocumentVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long documentId;
    @Lob
    @Column(columnDefinition="longblob")
    private byte[] documentImage;
    private String claimId;
    private String status="Pending...";
}
