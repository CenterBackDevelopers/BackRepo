package com.cbd.cbdtodo.exception.user;

/*
회원 가입과 아이디 중복 확인의 기능 분리를 위해 사용하지 않게 된 객체.
기록을 위해 남겨두었다.

서비스 객체의 signUpUser 메소드의 1번 검증 절차였다.
// 1. 아이디 중복 화인
        if(userRepository.existsByUserId(request.getUserId())) {
            throw new UserIdAlreadyExistsException();
        }
*/

public class UserIdAlreadyExistsException extends RuntimeException{
    public UserIdAlreadyExistsException() {
        super();
    }

    public UserIdAlreadyExistsException(String message) {
        super(message);
    }

    public UserIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
