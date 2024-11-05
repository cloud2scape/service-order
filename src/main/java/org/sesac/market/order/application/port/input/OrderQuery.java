package org.sesac.market.order.application.port.input;

import org.sesac.market.order.application.dto.request.ReadOrderRequest;
import org.sesac.market.order.application.dto.request.ReadOrdersRequest;
import org.sesac.market.order.domain.model.Order;
import org.springframework.data.domain.Page;

public interface OrderQuery {
    Order read(ReadOrderRequest query);

    Page<Order> read(ReadOrdersRequest query);
}
