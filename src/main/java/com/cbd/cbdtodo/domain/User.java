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

    public String getUserId() {
        return userId;
    }

    // 보안상의 이유로 getUserPassword 메소드는 만들지 않는 게 좋을 것 같아 로직을 User 객체 내부에 만들었음
    public void updatePassword(String passwordWantToChange) {
        if(passwordWantToChange.equals(this.userPassword)){
            throw new IllegalArgumentException("기존과 같은 비밀번호로 변경할 수 없습니다.");
        } else {
            this.userPassword = passwordWantToChange;
            this.userPasswordConfirm = passwordWantToChange;
        }
    }
}
