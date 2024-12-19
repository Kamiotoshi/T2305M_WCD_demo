package com.example.t2305m_springboot.dto.res;

import com.example.t2305m_springboot.dto.req.OrderItemReq;

import java.util.List;

public class OrderRes {
    private List<OrderItemReq> items;
    private String shippingAddress;
    private String telephone;

    public List<OrderItemReq> getItems() {
        return items;
    }

    public void setItems(List<OrderItemReq> items) {
        this.items = items;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}