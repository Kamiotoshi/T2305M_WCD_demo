package com.example.t2305m_springboot.controller;

import com.example.t2305m_springboot.dto.req.OrderReq;
import com.example.t2305m_springboot.dto.res.CategoryRes;
import com.example.t2305m_springboot.dto.res.OrderRes;
import com.example.t2305m_springboot.entity.Order;
import com.example.t2305m_springboot.entity.Product;
import com.example.t2305m_springboot.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutController {
    private final OrderService orderService;

    public CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping()
    public List<OrderRes> getAllOrders() {
        return orderService.all();
    }


    @PostMapping()
    public ResponseEntity<Order> checkout(@RequestBody OrderReq req){
        return ResponseEntity.ok(orderService.createOrder(req));
    }
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam Long status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId, @RequestParam String reason) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId, reason));
    }

    @GetMapping("/statistics/revenue")
    public ResponseEntity<Double> getTotalRevenue() {
        return ResponseEntity.ok(orderService.calculateTotalRevenue());
    }

    @GetMapping("/statistics/best-selling-product")
    public ResponseEntity<Product> getBestSellingProduct() {
        return ResponseEntity.ok(orderService.getBestSellingProduct());
    }

    @GetMapping("/statistics/highest-revenue-category")
    public ResponseEntity<CategoryRes> getHighestRevenueCategory() {
        return ResponseEntity.ok(orderService.getHighestRevenueCategory());
    }
}