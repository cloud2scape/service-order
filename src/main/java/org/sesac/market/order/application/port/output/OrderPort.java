package org.sesac.market.order.application.port.output;

import org.sesac.market.order.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderPort {
    Order save(Order order);

    void delete(Order order);

    Optional<Order> get(Long id);

    Page<Order> getMultiple(Pageable pageable);

    boolean exists(Long id);

}