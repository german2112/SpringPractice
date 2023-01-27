package com.germant.springPractice.exception;

public class NoSuchPersonExistException extends RuntimeException{

    public NoSuchPersonExistException() {}

    public NoSuchPersonExistException(String msg) {
        super(msg);
    }
}
