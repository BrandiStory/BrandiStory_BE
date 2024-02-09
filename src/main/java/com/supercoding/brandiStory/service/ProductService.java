package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.Entity.ProductEntity;
import com.supercoding.brandiStory.repository.products.ProductJpaRepository;
import com.supercoding.brandiStory.service.mapper.ProductMapper;
import com.supercoding.brandiStory.web.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ProductJpaRepository productJpaRepository;

    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> productEntities = productJpaRepository.findAll();
        if (productEntities.isEmpty()) throw new NotFoundException("등록된 상품이 없습니다.");
        return productEntities.stream().map(ProductMapper.INSTANCE::productEntityToProductDTO).collect(Collectors.toList());
    }
}