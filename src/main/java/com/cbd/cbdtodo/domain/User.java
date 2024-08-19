package com.cbd.cbdtodo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @Column
    private String userId;

    @Column
    private String userPassword;

    @Column
    private String userPasswordConfirm;

    @Column
    private String userName;

    // String, LoacalDate, java.util.date, jdva.sql.date 고민중
//    @Column
//    private String date;

    protected User() {};

    public User(String userId, String userPassword, String userPasswordConfirm, String userName) {
        if(userId == null || userId.isBlank()
                || userPassword == null || userPassword.isBlank()
                || userPasswordConfirm == null || userPasswordConfirm.isBlank()
                || userName == null || userName.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.userId = userId;
        this.userPassword = userPassword;
        this.userPasswordConfirm = userPasswordConfirm;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
