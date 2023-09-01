package com.bfs.claimservice.service;

import com.bfs.claimservice.dto.PolicyHolderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "POLICY-SERVICE")
public interface PolicyHolderApiClient {
    @GetMapping("api/holder/details/get/byId/{id}")
    PolicyHolderDto getPolicyHolderDetails(@PathVariable("id")String claimId);
}
