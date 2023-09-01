package com.cps.claimdisbursementservice.controller;

import com.cps.claimdisbursementservice.entity.ClaimDisbursement;
import com.cps.claimdisbursementservice.service.ClaimDibursementService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("api/claim/disburse")
public class ClaimDisbursementController {

    private final ClaimDibursementService claimDisbursementService;

    @PostMapping("/initiate/{claimId}")
    public ResponseEntity<?> initiateClaimDisbursement(@PathVariable String claimId){
        ClaimDisbursement claimDisbursement = claimDisbursementService.initiateClaim(claimId);
        if(Objects.nonNull(claimDisbursement)){
            return new ResponseEntity<String>("Claim Settlement Initiated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Please Provide Valid Data To Initiate Claim", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getClaimDisbursementStatus/{claimId}")
    public ResponseEntity<?> getAllClaimsToDisburse(@PathVariable String claimId){
        return new ResponseEntity<String>(claimDisbursementService.getClaimDisbursementStatus(claimId), HttpStatus.OK);
    }

    @GetMapping("/getAllClaimsToDisburse")
    public ResponseEntity<?> getAllClaimsToDisburse(){
        return new ResponseEntity<List<ClaimDisbursement>>(claimDisbursementService.getAllClaimsToDisburse(), HttpStatus.OK);
    }

    @PutMapping("/settle/{claimId}")
    public ResponseEntity<?> settleClaim(@PathVariable String claimId, @RequestParam("status") String status){
        String response = claimDisbursementService.settleClaim(claimId, status);
        if(response.equals("Status Updated"))
            return new ResponseEntity<String>(response, HttpStatus.OK);
        return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> fallbackMethod(String status,RuntimeException ex){
        return new ResponseEntity<String>("OOPS Server is Down",HttpStatus.OK);
    }

}
