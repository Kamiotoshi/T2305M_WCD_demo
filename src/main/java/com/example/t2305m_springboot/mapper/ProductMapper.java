package com.example.t2305m_springboot.mapper;
import com.example.t2305m_springboot.dto.req.ProductReq;
import com.example.t2305m_springboot.dto.res.ProductRes;
import com.example.t2305m_springboot.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface   ProductMapper {
    Product toEntity(ProductReq productReq);
    ProductRes toDTO(Product product);

}
