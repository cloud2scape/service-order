package org.sesac.market.order.application.dto.response;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
public record ReadOrdersResponse(
        Long id,
        Long price,
        Integer quantity,
        OffsetDateTime orderDate
) {
}
