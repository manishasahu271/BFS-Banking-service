package com.bfs.claimtrackingservice.service;

import com.bfs.claimtrackingservice.model.TrackingClaim;

public interface TrackingClaimService {
    public TrackingClaim createNewTrackClaim(TrackingClaim trackingClaim);
    public String trackClaimStatus(Long trackId);
    public String updateTrackingStatus(String claimId,String status);
}
