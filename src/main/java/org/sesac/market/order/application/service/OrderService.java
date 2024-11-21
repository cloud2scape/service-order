package org.sesac.market.order.application.service;

import lombok.RequiredArgsConstructor;
import org.sesac.market.order.application.dto.request.*;
import org.sesac.market.order.application.port.input.OrderCommand;
import org.sesac.market.order.application.port.input.OrderQuery;
import org.sesac.market.order.application.port.output.OrderPort;
import org.sesac.market.order.domain.event.Events;
import org.sesac.market.order.domain.event.OrderPlacedEvent;
import org.sesac.market.order.domain.exception.BizException;
import org.sesac.market.order.domain.model.Order;
import org.sesac.market.order.domain.model.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService implements OrderCommand, OrderQuery {
    private final OrderPort port;
    private final ProductService productService;

    @Override
    @Transactional
    public Order create(CreateOrderRequest request) {
        if (!productService.checkProductExists(request.productId())) {
            throw new BizException.NoneExists();
        }

        Order order = port.save(Order.builder()
                .accountId(request.accountId())
                .productId(request.productId())
                .price(request.price())
                .quantity(request.quantity())
                .orderState(OrderState.PENDING)
                .build());

        Events.raise(OrderPlacedEvent.builder()
                .orderId(order.getId())
                .accountId(order.getAccountId())
                .productId(order.getProductId())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build());

        return order;
    }

    @Override
    @Transactional
    public boolean delete(DeleteOrderRequest request) {
        if (!port.exists(request.id())) {
            throw new BizException.NoneExists();
        }

        Order order = Order.builder()
                .id(request.id())
                .build();

        port.delete(order);
        return true;
    }

    @Override
    @Transactional
    public Order updateOrderState(UpdateOrderStateRequest request) {
        Order order = port.get(request.id())
                .orElseThrow(BizException.NoneExists::new);

        return order.changeOrderState(request.state());
    }

    @Override
    public Order read(ReadOrderRequest query) {
        return port.get(query.id())
                .orElseThrow(BizException.NoneExists::new);
    }

    @Override
    public Page<Order> read(ReadOrdersRequest query) {
        return port.getMultiple(query.pageable());
    }
}
