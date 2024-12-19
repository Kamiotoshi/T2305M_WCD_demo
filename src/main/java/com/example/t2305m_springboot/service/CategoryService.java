package com.example.t2305m_springboot.service;

import com.example.t2305m_springboot.dto.req.CategoryReq;
import com.example.t2305m_springboot.dto.res.CategoryRes;
import com.example.t2305m_springboot.entity.Category;
import com.example.t2305m_springboot.mapper.CategoryMapper;
import com.example.t2305m_springboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryRes> all(){
        return categoryRepository.findAll().stream().map(
                categoryMapper::toDTO
        ).toList();
    }

    public CategoryRes create(CategoryReq category){
        return categoryMapper.toDTO(
                categoryRepository.save(
                        categoryMapper.toEntity(category)));
    }
    public List<CategoryRes> searchByName(String s){
        return categoryRepository.findAllByNameContains(s).stream().map(categoryMapper::toDTO).toList();
    }
}