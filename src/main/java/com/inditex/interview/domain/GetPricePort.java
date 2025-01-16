package com.inditex.interview.domain;

import com.inditex.interview.domain.model.Price;
import com.inditex.interview.domain.model.PriceQuery;

import java.util.Optional;

public interface GetPricePort {
    Optional<Price> getApplicablePrice(PriceQuery priceQuery);
}
