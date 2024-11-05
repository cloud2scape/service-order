package org.sesac.market.order.application.port.input;

import org.sesac.market.order.application.dto.request.CreateOrderRequest;
import org.sesac.market.order.application.dto.request.DeleteOrderRequest;
import org.sesac.market.order.domain.model.Order;

public interface OrderCommand {
    Order create(CreateOrderRequest request);

    boolean delete(DeleteOrderRequest request);
}
