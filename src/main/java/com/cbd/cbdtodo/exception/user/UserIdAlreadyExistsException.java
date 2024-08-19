package com.cbd.cbdtodo.exception.user;

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
