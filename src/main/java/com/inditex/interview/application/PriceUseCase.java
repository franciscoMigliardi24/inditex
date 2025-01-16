package com.inditex.interview.application;

import com.inditex.interview.domain.GetPricePort;
import com.inditex.interview.domain.PriceRepositoryPort;
import com.inditex.interview.domain.model.Price;
import com.inditex.interview.domain.model.PriceQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PriceUseCase implements GetPricePort {

    private final PriceRepositoryPort priceRepository;

    public PriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepository = priceRepositoryPort;
    }

    @Override
    public Optional<Price> getApplicablePrice(PriceQuery priceQuery) {
        List<Price> prices = priceRepository.findApplicablePrices(
                priceQuery.brandId().value(),
                priceQuery.productId().value().longValue(),
                priceQuery.applicationDate().value());

        if (prices.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(prices.getFirst());
    }
}
