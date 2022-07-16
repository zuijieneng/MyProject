package com.edu.zut.exception;

public class UserLoginException extends RuntimeException {
    public UserLoginException(String mess){
        super(mess);
    }
}
