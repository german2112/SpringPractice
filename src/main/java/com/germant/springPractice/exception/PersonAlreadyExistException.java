package com.germant.springPractice.exception;

public class PersonAlreadyExistException extends RuntimeException{
     public PersonAlreadyExistException() {}

    public PersonAlreadyExistException(String msg) {
         super(msg);
    }
}
