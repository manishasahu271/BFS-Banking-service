package com.bfs.claimtrackingservice.controller;

import com.bfs.claimtrackingservice.model.TrackingClaim;
import com.bfs.claimtrackingservice.service.TrackingClaimService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tracking")
@AllArgsConstructor
public class TrackingClaimServiceController {
    private TrackingClaimService trackingClaimService;
    @PostMapping("/new-track")
    public ResponseEntity<TrackingClaim> createNewTracking(@RequestBody TrackingClaim trackingClaim){
        return new ResponseEntity<>(trackingClaimService.createNewTrackClaim(trackingClaim), HttpStatus.CREATED);
    }
    @GetMapping("/track-status/{trackId}")
    public ResponseEntity<String> checkTracking(@PathVariable Long trackId){
        return new ResponseEntity<>(trackingClaimService.trackClaimStatus(trackId),HttpStatus.OK);
    }
    @PutMapping("/update-track/{claimId}")
    public ResponseEntity<?> updateTrackingStatus(@PathVariable String claimId,@RequestParam String status){
        String response=trackingClaimService.updateTrackingStatus(claimId,status);
        if(response.equals("status updated"))
        return new ResponseEntity<String>(response,HttpStatus.OK);
        return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
    }
}
