package com.example.t2305m_springboot.controller;

import com.example.t2305m_springboot.dto.req.CategoryReq;
import com.example.t2305m_springboot.dto.res.CategoryRes;
import com.example.t2305m_springboot.entity.Category;
import com.example.t2305m_springboot.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<CategoryRes> getAllCategory(){
        return categoryService.all();
    }

    @PostMapping()
    public ResponseEntity<CategoryRes> createCategory(@RequestBody CategoryReq category){
        return ResponseEntity.ok(categoryService.create(category));
    }

//    @GetMapping(name = "search")
//    public List<CategoryRes> search(@RequestBody String s){
//        return categoryService.searchByName(s);
//    }

}