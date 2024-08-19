package com.cbd.cbdtodo.dto.user;

public class UserSignupRequest {


    private String userId;

    private String userPassword;
    private String userPasswordConfirm;

    private String userName;


    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPasswordConfirm() {
        return userPasswordConfirm;
    }

    public String getUserName() {
        return userName;
    }
}
