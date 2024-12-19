package com.example.t2305m_springboot.mapper;

import com.example.t2305m_springboot.dto.req.CategoryReq;
import com.example.t2305m_springboot.dto.res.CategoryRes;
import com.example.t2305m_springboot.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryReq categoryReq);
    CategoryRes toDTO(Category category);
}