package com.inditex.interview.infrastructure.controller.rest;

import com.inditex.interview.application.PriceUseCase;
import com.inditex.interview.domain.GetPricePort;
import com.inditex.interview.domain.model.ApplicationDate;
import com.inditex.interview.domain.model.BrandId;
import com.inditex.interview.domain.model.PriceQuery;
import com.inditex.interview.domain.model.ProductId;
import com.inditex.interview.generated.infrastructure.controllers.rest.GetApplicablePriceApi;
import com.inditex.interview.generated.infrastructure.controllers.rest.model.PriceResponseDto;
import com.inditex.interview.infrastructure.controller.rest.error.PriceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class PriceController implements GetApplicablePriceApi {

    private final GetPricePort priceUseCase;

    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    @Override
    public ResponseEntity<PriceResponseDto> getApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        PriceQuery priceQuery = new PriceQuery(new BrandId(brandId), new ProductId(productId), new ApplicationDate(applicationDate));
        return priceUseCase.getApplicablePrice(priceQuery)
                .map(price -> new PriceResponseDto()
                        .productId(price.productId().intValue())
                        .brandId(price.brandId())
                        .priceList(price.priceList())
                        .startDate(price.startDate())
                        .endDate(price.endDate())
                        .priority(price.priority())
                        .price(price.price())
                        .currency(price.currency())
                )
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PriceNotFoundException("No hay precio aplicable para esos parámetros"));
    }

    record PriceResponse(
            Long productId,
            Integer brandId,
            Integer priceList,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer priority,
            Double price,
            String currency
    ) {}

}
