package com.cbd.cbdtodo.dto.user;

public class UserLoginRequest {

    private String userId;

    private String userPassword;

    private boolean autologin;

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isAutologin() {
        return autologin;
    }

    // @ModelAttrubite 사용시 setter 메소드가 반드시 있어야 한다.
    // 하긴 조금만 생각해보면 당연한거긴해..

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setAutologin(boolean autologin) {
        this.autologin = autologin;
    }
}
