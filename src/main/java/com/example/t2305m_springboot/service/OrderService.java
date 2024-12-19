package com.example.t2305m_springboot.service;

import com.example.t2305m_springboot.dto.req.OrderReq;
import com.example.t2305m_springboot.dto.res.CategoryRes;
import com.example.t2305m_springboot.dto.res.OrderRes;
import com.example.t2305m_springboot.entity.Order;
import com.example.t2305m_springboot.entity.OrderItem;
import com.example.t2305m_springboot.entity.Product;
import com.example.t2305m_springboot.mapper.OrderMapper;
import com.example.t2305m_springboot.repository.OrderRepository;
import com.example.t2305m_springboot.repository.ProductRepository;
import com.example.t2305m_springboot.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final ReviewRepository reviewRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper,ReviewRepository reviewRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.reviewRepository = reviewRepository;
    }
    public List<OrderRes> all(){
        return orderRepository.findAll().stream().map(
                orderMapper::toDTO
        ).toList();
    }

    @Transactional
    public Order createOrder(OrderReq orderReq){
        Order order = new Order();
        order.setGrandTotal(0.0);
        List<OrderItem> items = orderReq.getItems().stream().map(
                item-> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(item.getProductId());
                    orderItem.setQty(item.getQty());
                    Product p = productRepository.findById(item.getProductId()).orElseThrow(
                            ()-> new RuntimeException("Product not found: "+item.getProductId())
                    );
                    if(p.getQty() < item.getQty())
                        throw new RuntimeException("Insufficient stock for product: "+p.getName());
                    p.setQty(p.getQty() - item.getQty());
                    productRepository.save(p);
                    orderItem.setPrice(p.getPrice());
                    orderItem.setOrder(order);
                    order.setGrandTotal(order.getGrandTotal() + orderItem.getQty() * p.getPrice());
                    return orderItem;
                }
        ).toList();
        order.setItems(items);
        order.setCreateAt(new Date());
        order.setShippingAddress(orderReq.getShippingAddress());
        order.setTelephone(orderReq.getTelephone());
        return orderRepository.save(order);
    }
    @Transactional
    public Order updateOrderStatus(Long orderId, Long status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(Long orderId, String cancelReason) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        order.setStatus(0L); // Trạng thái hủy
        order.setCancelReason(cancelReason);
        return orderRepository.save(order);
    }

    public Double calculateTotalRevenue() {
        return orderRepository.findAll().stream()
                .mapToDouble(Order::getGrandTotal)
                .sum();
    }

    public Product getBestSellingProduct() {
        return productRepository.findBestSellingProduct(); // Custom query cần thêm trong `ProductRepository`
    }

    public CategoryRes getHighestRevenueCategory() {
        return productRepository.findHighestRevenueCategory(); // Custom query cần thêm trong `ProductRepository`
    }
}