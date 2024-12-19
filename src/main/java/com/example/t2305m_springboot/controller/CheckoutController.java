package com.example.t2305m_springboot.controller;

import com.example.t2305m_springboot.dto.req.OrderReq;
import com.example.t2305m_springboot.entity.Order;
import com.example.t2305m_springboot.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {
    private final OrderService orderService;

    public CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<Order> checkout(@RequestBody OrderReq req){
        return ResponseEntity.ok(orderService.createOrder(req));
    }
}