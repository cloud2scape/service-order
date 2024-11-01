package org.sesac.market.order.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {
    @GetMapping
    public String home() {
        return "<h2>Order Server</h2>";
    }
}
