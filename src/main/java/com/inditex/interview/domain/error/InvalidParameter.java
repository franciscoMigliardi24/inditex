package com.inditex.interview.domain.error;

public class InvalidParameter extends RuntimeException {

    public InvalidParameter(String message) {
        super(message);
    }
}
