package org.sesac.market.order.application.dto.request;

import lombok.Builder;
import org.sesac.market.order.domain.model.OrderState;

@Builder(toBuilder = true)
public record UpdateOrderStateRequest(
        Long id,
        Long productId,
        OrderState state
) {
}
