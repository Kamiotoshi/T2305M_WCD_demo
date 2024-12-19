package com.example.t2305m_springboot.service;

import com.example.t2305m_springboot.dto.req.OrderReq;
import com.example.t2305m_springboot.entity.Order;
import com.example.t2305m_springboot.entity.OrderItem;
import com.example.t2305m_springboot.entity.Product;
import com.example.t2305m_springboot.repository.OrderRepository;
import com.example.t2305m_springboot.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
}