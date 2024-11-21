package org.sesac.market.order.infrastructure.adapter.input.messaging.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sesac.market.order.application.dto.request.UpdateOrderStateRequest;
import org.sesac.market.order.application.service.OrderService;
import org.sesac.market.order.domain.event.OrderCanceledEvent;
import org.sesac.market.order.domain.model.Order;
import org.sesac.market.order.domain.model.OrderState;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "refund", groupId = "order")
    public void handleOrderPlacedEvent(OrderCanceledEvent event) {
        log.info("주문 취소 요청 받음: {}", event);

        UpdateOrderStateRequest updateOrderStateRequest = UpdateOrderStateRequest.builder()
                .id(event.orderId())
                .productId(event.productId())
                .state(OrderState.CANCELED)
                .build();

        Order order = orderService.updateOrderState(updateOrderStateRequest);
        log.info("주문 취소 완료: {}", order);
    }
}
