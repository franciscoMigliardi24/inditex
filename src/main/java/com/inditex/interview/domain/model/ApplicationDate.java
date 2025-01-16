package com.inditex.interview.domain.model;

import java.time.LocalDateTime;

public record ApplicationDate(LocalDateTime value) {
    public ApplicationDate {
        if (value == null) {
            throw new IllegalArgumentException("Application date cannot be null");
        }
    }
}
