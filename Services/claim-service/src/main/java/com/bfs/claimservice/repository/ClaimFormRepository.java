package com.bfs.claimservice.repository;

import com.bfs.claimservice.model.ClaimForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimFormRepository extends JpaRepository<ClaimForm,String> {
}
