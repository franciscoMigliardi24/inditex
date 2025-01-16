package com.inditex.interview.domain.model;

import com.inditex.interview.domain.error.InvalidParameter;

import java.util.Objects;

public record ProductId(Integer value) {

    public ProductId {
        if (Objects.isNull(value)) {
            throw new InvalidParameter("ProductId is required");
        }
    }
}
