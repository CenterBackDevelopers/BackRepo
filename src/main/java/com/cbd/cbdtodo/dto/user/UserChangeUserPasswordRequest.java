package com.cbd.cbdtodo.dto.user;

public class UserChangeUserPasswordRequest {

    private String userId;

    private String userPassword;

    private String userPasswordWantToChange;

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPasswordWantToChange() {
        return userPasswordWantToChange;
    }
}
