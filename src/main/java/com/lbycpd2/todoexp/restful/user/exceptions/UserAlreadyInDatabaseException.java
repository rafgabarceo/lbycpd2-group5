package com.lbycpd2.todoexp.restful.user.exceptions;

public class UserAlreadyInDatabaseException extends Exception {
    public UserAlreadyInDatabaseException(String message){
        super(message);
    }
}
