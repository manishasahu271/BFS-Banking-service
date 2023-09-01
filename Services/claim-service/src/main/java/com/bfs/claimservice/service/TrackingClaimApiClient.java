package com.bfs.claimservice.service;


import com.bfs.claimservice.dto.TrackingClaimDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "TRACKING-SERVICE")
public interface TrackingClaimApiClient {
    @PostMapping("api/tracking/new-track")
TrackingClaimDto createNewTrackClaim(TrackingClaimDto trackingClaimDto);
    @GetMapping("api/tracking/track-status/{trackId}")
String checkTracking(Long trackId);
}
