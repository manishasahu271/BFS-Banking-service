package com.bfs.claimservice.exception;

public class NoPolicyDetailsFoundException extends Exception{
    private String errorMessage;
    public NoPolicyDetailsFoundException(String errorMessage){
        this.errorMessage=errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }

}
