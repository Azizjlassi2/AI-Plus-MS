package com.aiplus.user.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("Email '" + email + "' is already in use.");
    }

    public EmailAlreadyUsedException() {
        super("Email is already in use.");
    }

}
