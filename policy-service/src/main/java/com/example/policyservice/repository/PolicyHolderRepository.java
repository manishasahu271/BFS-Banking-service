package com.example.policyservice.repository;

import com.example.policyservice.entity.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, String> {
}
