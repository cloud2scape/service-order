package org.sesac.market.order.application.dto.response;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
public record ReadOrderResponse(
        Long id,
        Long productId,
        UUID accountId,
        Long price,
        Integer quantity,
        OffsetDateTime orderDate
) {
}
