package com.bfs.claimservice.exception;

public class NoClaimDetailsFoundException extends Exception{
    private String errorMessage;
    public NoClaimDetailsFoundException(String errorMessage){
        this.errorMessage=errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}
