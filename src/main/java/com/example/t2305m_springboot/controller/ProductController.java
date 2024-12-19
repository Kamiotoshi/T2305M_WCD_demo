package com.example.t2305m_springboot.controller;

import com.example.t2305m_springboot.dto.req.ProductReq;
import com.example.t2305m_springboot.dto.res.ProductRes;
import com.example.t2305m_springboot.entity.Product;
import com.example.t2305m_springboot.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<ProductRes> getAllProduct() {
        return productService.all();
    }
    @PostMapping()
    public ResponseEntity<ProductRes> createProduct(@RequestBody ProductReq product) {
        return ResponseEntity.ok(productService.create(product));
    }
    @PostMapping("search")
    public List<Product> search(@RequestBody(required = false) String name,
                                @RequestBody(required = false) Double minPrice,
                                @RequestBody(required = false) Double maxPrice) {
        return productService.fitler(name, minPrice, maxPrice);
    }
}
