package com.bfs.claimtrackingservice.service.impl;

import com.bfs.claimtrackingservice.model.TrackingClaim;
import com.bfs.claimtrackingservice.repository.TrackingServiceRepository;
import com.bfs.claimtrackingservice.service.TrackingClaimService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TrackingClaimServiceImpl implements TrackingClaimService {
    private TrackingServiceRepository trackingServiceRepository;
    @Override
    public TrackingClaim createNewTrackClaim(TrackingClaim trackingClaim) {
        TrackingClaim existClaim=trackingServiceRepository.findByClaimId(trackingClaim.getClaimId());
        if(existClaim==null)
        return trackingServiceRepository.save(trackingClaim);
        return trackingServiceRepository.findByClaimId(trackingClaim.getClaimId());
    }

    @Override
    public String trackClaimStatus(Long trackId) {
        TrackingClaim trackingClaim=trackingServiceRepository.findById(trackId).get();
        return trackingClaim.getClaimStatus();
    }

    @Override
    public String updateTrackingStatus(String claimId,String status) {
        if(Objects.nonNull(claimId)){
            List<TrackingClaim> claims=trackingServiceRepository.findAll();
            if(claims.stream().anyMatch(claim->claim.getClaimId().equals(claimId))){
                TrackingClaim claim=trackingServiceRepository.findByClaimId(claimId);
                claim.setClaimStatus(status);
                trackingServiceRepository.save(claim);
                return "status updated";
            }
            return "No claim Id found";
        }
        return "please enter valid claim id";
    }
}
