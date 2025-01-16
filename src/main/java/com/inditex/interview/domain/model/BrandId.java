package com.inditex.interview.domain.model;

import com.inditex.interview.domain.error.InvalidParameter;

import java.util.Objects;

public record BrandId(Integer value) {

    public BrandId {
        if (Objects.isNull(value)) {
            throw new InvalidParameter("BrandId is required");
        }
    }
}
