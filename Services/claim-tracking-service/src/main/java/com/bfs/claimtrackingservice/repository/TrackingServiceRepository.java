package com.bfs.claimtrackingservice.repository;

import com.bfs.claimtrackingservice.model.TrackingClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingServiceRepository extends JpaRepository<TrackingClaim,Long> {
    TrackingClaim findByClaimId(String claimId);
}
