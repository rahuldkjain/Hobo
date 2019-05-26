package com.hobo.user.exceptions.user;

public class UserNotFound extends Exception {
    public UserNotFound(String error){
        super(error);
    }
}