package com.inditex.interview.infrastructure.controller.rest.error;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String msg) {
        super(msg);
    }
}