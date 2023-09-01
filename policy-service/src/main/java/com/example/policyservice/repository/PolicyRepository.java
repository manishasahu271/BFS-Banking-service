package com.example.policyservice.repository;

import com.example.policyservice.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, String> {
    Optional<Policy> findByPolicyName(String policyName);
}
