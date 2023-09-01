package com.bfs.claimservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlePoint {
    @ExceptionHandler(NoPolicyDetailsFoundException.class)
    public ResponseEntity<String> noPolicyFoundException(NoPolicyDetailsFoundException ex){
        return new ResponseEntity<String>(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoClaimDetailsFoundException.class)
    public ResponseEntity<String> noClaimFoundException(NoClaimDetailsFoundException ex){
        return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.NOT_FOUND);
    }
}
