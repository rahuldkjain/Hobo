package com.hobo.user.exceptions.user;

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String error) {
        super(error);
    }
}