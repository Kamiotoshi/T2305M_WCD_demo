package com.example.t2305m_springboot.mapper;


import com.example.t2305m_springboot.dto.req.OrderReq;
import com.example.t2305m_springboot.dto.res.OrderRes;
import com.example.t2305m_springboot.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderReq orderReq);
    OrderRes toDTO(Order order);
}
