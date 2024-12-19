package com.example.t2305m_springboot.service;

import com.example.t2305m_springboot.dto.req.ProductReq;
import com.example.t2305m_springboot.dto.res.ProductRes;
import com.example.t2305m_springboot.entity.Product;
import com.example.t2305m_springboot.mapper.ProductMapper;
import com.example.t2305m_springboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    public List<ProductRes> all(){
        return productRepository.findAll().stream().map(
                productMapper::toDTO
        ).toList();
    }
    public ProductRes create(ProductReq product) {
        return productMapper.toDTO(
                productRepository.save(
                        productMapper.toEntity(product)
                )
        );
    }
    public List<Product> fitler(String name,Double minPrice,Double maxPrice){
        return productRepository.filter(name,minPrice,maxPrice);
    }
}
